package com.steeplesoft.jooq.basic.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

@RequestScoped
public class DslContextProvider {
    private static DSLContext context;

    public static DSLContext getDslContext() {
        if (context == null) {
            try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:mem:jooq_demo",
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

                context = DSL.using(configuration);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return context;
    }

    private static void buildDatabase(Connection conn) throws IOException, SQLException {
        try (
                BufferedReader rr = new BufferedReader(
                        new InputStreamReader(DslContextProvider.class.getClassLoader().getResourceAsStream("/jooq_demo.sql")));
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

    /*
    @Produces
    public DSLContext getDslContext() {
        Configuration configuration = new DefaultConfiguration()
                .set(dataSource)
                .set(SQLDialect.POSTGRES)
                .set(new Settings()
                        .withExecuteLogging(true)
                        .withRenderCatalog(false)
                        .withRenderSchema(false)
                        .withRenderQuotedNames(RenderQuotedNames.NEVER)
                        .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED));

        return DSL.using(configuration);
    }
    */
}
