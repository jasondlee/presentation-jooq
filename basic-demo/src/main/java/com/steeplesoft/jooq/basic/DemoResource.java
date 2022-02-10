package com.steeplesoft.jooq.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.steeplesoft.jooq.basic.model.Author;
import com.steeplesoft.jooq.basic.model.Book;
import com.steeplesoft.jooq.basic.model.FullBook;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {
    private DSLContext dsl = getDslContext();

    @GET
    @Path("/authors")
    public List<Author> getAuthors() {
        return Arrays.stream(dsl.select(
                                DSL.field("id"),
                                DSL.field("last_name"),
                                DSL.field("first_name")
                        )
                        .from("authors")
                        .fetchArray())
                .map(r -> Author.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/books")
    public List<Book> getBooks(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("year") Integer publishedYear
    ) {
        SelectConditionStep query = dsl.select(
                        DSL.field("id"),
                        DSL.field("title"),
                        DSL.field("description"),
                        DSL.field("published_year")
                )
                .from("books")
                .where("1=1");
        if (title != null) {
            query = query.and(DSL.field("title").like("%" + title + "%"));
        }
        if (description != null) {
            query = query.and(DSL.field("description").like("%" + description + "%"));
        }
        if (publishedYear != null) {
            query = query.and(DSL.field("published_year").eq(publishedYear));
        }
        System.out.println(query.getSQL());
        return Arrays.stream(query.fetchArray())
                .map(r -> Book.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/books/{author}")
    public List<FullBook> getBooksByAuthor(@PathParam("author") String author) {
        return Arrays.stream(dsl.select(
                                DSL.field("books.id"),
                                DSL.field("books.title"),
                                DSL.field("books.description"),
                                DSL.field("books.published_year"),
                                DSL.field("authors.id"),
                                DSL.field("authors.first_name"),
                                DSL.field("authors.last_name")
                        )
                        .from("books")
                        .join("authors")
                        .on("books.author_id = authors.id")
                        .fetchArray())
                .map(r -> FullBook.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/booksAndAuthors")
    public List<FullBook> getFullBooks(@QueryParam("author") String author) {
        SelectConditionStep query = dsl.select(
                        DSL.field("books.id"),
                        DSL.field("books.title"),
                        DSL.field("books.description"),
                        DSL.field("books.published_year"),
                        DSL.field("authors.id"),
                        DSL.field("authors.first_name"),
                        DSL.field("authors.last_name")
                )
                .from("books")
                .join("authors")
                .on("books.author_id = authors.id")
                .where("1=1");
        if (author != null) {
            query = query.and("authors.last_name = ?", author);

        }
        return Arrays.stream(query.fetchArray())
                .map(r -> FullBook.fromRecord(r))
                .collect(Collectors.toList());
    }

    private DSLContext getDslContext() {
        try {
            String dbloc = System.getProperty("jooq.db", "/tmp/blargh");
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:file:" + dbloc,
                    "jooq_demo", "jooq_demo");

            buildDatabase(conn);

            Configuration configuration = new DefaultConfiguration()
                    .set(conn)
                    .set(SQLDialect.POSTGRES)
                    .set(new Settings()
                            .withExecuteLogging(true)
                            .withRenderCatalog(false)
                            .withRenderSchema(false)
                            .withRenderQuotedNames(RenderQuotedNames.NEVER)
                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
                    );

            return DSL.using(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void buildDatabase(Connection conn) throws IOException, SQLException {
        try (
                BufferedReader rr = new BufferedReader(
                        new InputStreamReader(getClass().getClassLoader().getResourceAsStream("/jooq_demo.sql")));
        ) {
            String line;
            StringBuilder ddl = new StringBuilder();
            while (null != (line = rr.readLine())) {
                line = line.strip();
                if (!line.startsWith("--")) {
                    ddl.append(line);
                }
            }

            conn.createStatement().execute(ddl.toString());
        }
    }
}
