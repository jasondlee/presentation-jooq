package com.steeplesoft.jooq.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.steeplesoft.jooq.basic.model.Author;
import com.steeplesoft.jooq.basic.model.Book;
import com.steeplesoft.jooq.basic.model.FullBook;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
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
        return Arrays.stream(dsl.select()
                        .from("authors")
                        .fetchArray())
                .map(r -> Author.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/books")
    public List<Book> getBooks() {
        return Arrays.stream(dsl.select()
                        .from("books")
                        .fetchArray())
                .map(r -> Book.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/booksAndAuthors")
    public List<FullBook> getFullBooks() {
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

    private DSLContext getDslContext() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jooq_demo",
                    "jooq_demo", "jooq_demo");
            Configuration configuration = new DefaultConfiguration()
                    .set(conn)
                    .set(SQLDialect.POSTGRES)
                    .set(new Settings()
                            .withExecuteLogging(true)
                            .withRenderCatalog(false)
                            .withRenderSchema(false)
                            .withRenderQuotedNames(RenderQuotedNames.NEVER)
                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED));

            return DSL.using(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
