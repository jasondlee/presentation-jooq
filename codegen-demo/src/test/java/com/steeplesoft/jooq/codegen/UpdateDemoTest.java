package com.steeplesoft.jooq.codegen;

import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

public class UpdateDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void update() {
        int count = dsl.update(CUSTOMER)
                .set(CUSTOMER.ACTIVEBOOL, false)
                .set(CUSTOMER.ACTIVE, 0)
                .where(CUSTOMER.CUSTOMER_ID.eq(1))
                .execute();
    }
}
