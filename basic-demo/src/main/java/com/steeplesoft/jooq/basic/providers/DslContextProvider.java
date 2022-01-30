package com.steeplesoft.jooq.basic.providers;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DslContextProvider {
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
