package com.steeplesoft.jooq.codegen;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.jupiter.api.Test;

import static com.steeplesoft.jooq_demo.generated.Tables.ADDRESS;
import static com.steeplesoft.jooq_demo.generated.Tables.AUTHORS;
import static com.steeplesoft.jooq_demo.generated.Tables.BOOKS;
import static com.steeplesoft.jooq_demo.generated.Tables.STAFF;
import static com.steeplesoft.jooq_demo.generated.Tables.STORE;

public class JoinDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void crossJoin() {
        Result<Record> results = dsl.select()
                .from(STORE)
                .crossJoin(STAFF)
                .fetch();

        System.out.println(results);
    }

    @Test
    public void innerJoin() {
        Result<Record> results = dsl.select()
                .from(STORE.join(STAFF).on(STORE.STORE_ID.eq(STAFF.STORE_ID)))
                .fetch();

        System.out.println(results);
    }

    @Test
    public void leftOuterJoin() {
        Result<Record> results = dsl.select()
                .from(AUTHORS)
                .leftOuterJoin(BOOKS).on(AUTHORS.ID.eq(BOOKS.AUTHOR_ID))
                .fetch();

        System.out.println(results);
    }

    @Test
    public void rightOuterJoin() {
        Result<Record> results = dsl.select()
                .from(AUTHORS)
                .rightOuterJoin(BOOKS).on(AUTHORS.ID.eq(BOOKS.AUTHOR_ID))
                .fetch();

        System.out.println(results);
    }

    @Test
    public void fullOuterJoin() {
        Result<Record> results = dsl.select()
                .from(AUTHORS)
                .fullOuterJoin(BOOKS).on(AUTHORS.ID.eq(BOOKS.AUTHOR_ID))
                .fetch();

        System.out.println(results);
    }

    @Test
    public void implicitJoin() {
        Result results = dsl.select(
                        STAFF.STAFF_ID,
                        STAFF.LAST_NAME,
                        STAFF.FIRST_NAME,
                        STAFF.store().STORE_ID,
                        STAFF.store().address().ADDRESS_,
                        STAFF.store().address().city().CITY_
                ).from(STAFF)
                .orderBy(
                        STAFF.store().address().city().CITY_,
                        STAFF.LAST_NAME, STAFF.FIRST_NAME
                )
                .fetch();

        System.out.println(results);
    }

    @Test
    public void count() {
        System.out.println(dsl.fetchCount(ADDRESS));
    }
}
