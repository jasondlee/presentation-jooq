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
    @GET
    public List<Author> getAuthors() throws Exception {
        DSLContext dsl = getDslContext();

        return Arrays.stream(dsl.select()
                        .from("authors")
                        .fetchArray())
                .map(r -> {
                    Author author = new Author();
                    author.setId(r.get("id", Long.class));
                    author.setFirstName(r.get("first_name", String.class));
                    author.setLastName(r.get("last_name", String.class));

                    return author;
                })
                .collect(Collectors.toList());
    }

    private DSLContext getDslContext() throws Exception {
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
    }
}
