package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.mapper.SakilaRecordMapperProvider;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DslContextProvider {
    public static DSLContext getDslContext() {
        try {
            return DSL.using(getConfiguration());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DSLContext getDslContextWithMappers() {
        try {
            return DSL.using(getConfiguration()
                    .set(new SakilaRecordMapperProvider()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration getConfiguration() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        return new DefaultConfiguration()
                .set(DriverManager.getConnection("jdbc:postgresql://localhost/jooq_demo",
                        "jooq_demo", "jooq_demo"))
                .set(SQLDialect.POSTGRES)
                .set(new Settings()
                        .withExecuteLogging(true)
                        .withRenderFormatted(true)
                        .withRenderCatalog(false)
                        .withRenderSchema(false)
                        .withMaxRows(Integer.MAX_VALUE)
                        .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                        .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
                );
    }
}
