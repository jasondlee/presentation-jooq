package com.steeplesoft.jooq.codegen;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

@RequestScoped
class DslContextProvider {
    @Inject
    private DataSource dataSource;

    @Produces
    @RequestScoped
    public DSLContext getDslContext() {
        Configuration configuration = new DefaultConfiguration()
            .set(dataSource)
            .set(SQLDialect.POSTGRES)
            .set(
                new Settings()
                    .withExecuteLogging(true)
                    .withRenderCatalog(false)
                    .withRenderSchema(false)
                    .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                    .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
            );

        return DSL.using(configuration);
    }
}
