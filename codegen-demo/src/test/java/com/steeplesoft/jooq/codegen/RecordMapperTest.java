package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.CustomerModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;

public class RecordMapperTest {
    private DSLContext dsl = DslContextProvider.getDslContextWithMappers();

//    @Test
    public void actorMapper() {
        List<ActorModel> actors = dsl.selectFrom(ACTOR)
                .fetchInto(ActorModel.class);

        actors.forEach(System.out::println);
    }

//    @Test
    public void filmMapper() {
        List<FilmModel> films = dsl.select()
                .from(FILM)
                .where(FILM.TITLE.like("%THE%"))
                .fetchInto(FilmModel.class);

        System.out.println(films);
    }

//    @Test
    public void complexFilter() {
        List<CustomerModel> customers = dsl.select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq("MARION"))
                .and(CUSTOMER.LAST_NAME.eq("SNYDER"))
                .fetchInto(CustomerModel.class);

        System.out.println(customers);
    }

    @Test
    public void time() {
        System.out.println("time():");
        DSLContext noMap = DslContextProvider.getDslContext();
        int loopMax = 10000;
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        for (int i = 0; i < loopMax; i++) {
            dsl.selectFrom(ACTOR).fetchInto(ActorModel.class);
        }
        end = System.currentTimeMillis();
        System.out.println("    Mapper time: " + ((end-start) / 1000.0) );

        start = System.currentTimeMillis();
        for (int i = 0; i < loopMax; i++) {
            noMap.selectFrom(ACTOR).fetchInto(ActorModel.class);
        }
        end = System.currentTimeMillis();
        System.out.println("    Reflection time: " + ((end-start) / 1000.0) );


    }

    @Test
    public void time2() {
        DSLContext refl = DslContextProvider.getDslContext();
        int loopMax = 10000;
        long start = 0;
        long end = 0;

        for (int i = 0; i < loopMax; i++) {
            dsl.selectFrom(ACTOR).fetchInto(ActorModel.class);
        }

        int count = 0;
        while (count++ < 10) {
            System.out.println("time2():");
            start = System.currentTimeMillis();
            for (int i = 0; i < loopMax; i++) {
                dsl.selectFrom(ACTOR).fetchInto(ActorModel.class);
            }
            end = System.currentTimeMillis();
            System.out.println("    Mapper time: " + ((end - start) / 1000.0));

            start = System.currentTimeMillis();
            for (int i = 0; i < loopMax; i++) {
                refl.selectFrom(ACTOR).fetchInto(ActorModel.class);
            }
            end = System.currentTimeMillis();
            System.out.println("    Reflection time: " + ((end - start) / 1000.0));
        }
    }
}
