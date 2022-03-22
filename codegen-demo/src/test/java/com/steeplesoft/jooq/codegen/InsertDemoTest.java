package com.steeplesoft.jooq.codegen;

import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.junit.jupiter.api.Test;

public class InsertDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void insertValuesDemo() {
        dsl.insertInto(CUSTOMER,
                        CUSTOMER.CUSTOMER_ID, CUSTOMER.STORE_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME,
                        CUSTOMER.ADDRESS_ID, CUSTOMER.ACTIVE)
                .values(1000, 1, "Dummy", "User", 1, 1)
                .execute();
    }

    @Test
    public void insertReturningDemo() {
        Record1<Integer> record = dsl.insertInto(CUSTOMER,
                        CUSTOMER.STORE_ID, CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME,
                        CUSTOMER.ADDRESS_ID, CUSTOMER.ACTIVE)
                .values(1, "Dummy", "User", 1, 1)
                .returningResult(CUSTOMER.CUSTOMER_ID)
                .fetchOne();
        Integer key = record.get(CUSTOMER.CUSTOMER_ID);
        System.out.println(key);
    }

    @Test
    public void insertSetReturningDemo() {
        Record1<Integer> record = dsl.insertInto(CUSTOMER)
                .set(CUSTOMER.STORE_ID, 1)
                .set(CUSTOMER.FIRST_NAME, "Dummy")
                .set(CUSTOMER.LAST_NAME, "User")
                .set(CUSTOMER.ADDRESS_ID, 1)
                .set(CUSTOMER.ACTIVE, 1)
                .returningResult(CUSTOMER.CUSTOMER_ID)
                .fetchOne();
        Integer key = record.get(CUSTOMER.CUSTOMER_ID);
        System.out.println(key);
    }
}
