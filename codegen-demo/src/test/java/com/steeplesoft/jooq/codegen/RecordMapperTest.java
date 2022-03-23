package com.steeplesoft.jooq.codegen;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;

import java.util.List;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.CustomerModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

public class RecordMapperTest {
    private DSLContext dsl = DslContextProvider.getDslContextWithMappers();

    @Test
    public void actorMapper() {
        List<ActorModel> actors = dsl.selectFrom(ACTOR)
                .fetchInto(ActorModel.class);

        actors.forEach(System.out::println);
    }

    @Test
    public void filmMapper() {
        List<FilmModel> films = dsl.select()
                .from(FILM)
                .where(FILM.TITLE.like("%THE%"))
                .fetchInto(FilmModel.class);

        System.out.println(films);
    }

    @Test
    public void complexFilter() {
        List<CustomerModel> customers = dsl.select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq("MARION"))
                .and(CUSTOMER.LAST_NAME.eq("SNYDER"))
                .fetchInto(CustomerModel.class);

        System.out.println(customers);
    }
}
