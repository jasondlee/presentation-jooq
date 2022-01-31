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
