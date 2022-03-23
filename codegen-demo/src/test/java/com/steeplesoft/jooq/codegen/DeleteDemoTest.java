package com.steeplesoft.jooq.codegen;

import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

public class DeleteDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void deleteDemo() {
        int count = dsl.delete(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_ID.eq(1000))
                .execute();

        System.out.println("Rows deleted: " + count);
    }
}
