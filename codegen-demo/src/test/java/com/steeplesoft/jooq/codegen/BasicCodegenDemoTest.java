package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.CustomerModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import com.steeplesoft.jooq.codegen.model.FullFilmModel;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;
import static com.steeplesoft.jooq_demo.generated.tables.FilmActor.FILM_ACTOR;

public class BasicCodegenDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void simpleSelect() {
        List<ActorModel> actors = dsl.fetch(ACTOR)
                .map(r -> ActorModel.fromRecord(r));

        System.out.println(actors);
    }

    @Test
    public void simpleFilter() {
        List<FilmModel> films = dsl.select()
                .from(FILM)
                .where(FILM.TITLE.like("%THE%"))
                .fetch().map(r -> FilmModel.fromRecord(r));

        System.out.println(films);
    }

    @Test
    public void complexFilter() {
        List<CustomerModel> customers = dsl.select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq("MARION"))
                .and(CUSTOMER.LAST_NAME.eq("SNYDER"))
                .fetch().map(r -> CustomerModel.fromRecord(r));

        System.out.println(customers);
    }

    @Test
    public void moreComplexFilter() {
        List<CustomerModel> customers = dsl.select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq("MARION")
                        .and(CUSTOMER.LAST_NAME.eq("SNYDER")))
                .or(CUSTOMER.FIRST_NAME.eq("TERRY")
                        .and(CUSTOMER.LAST_NAME.eq("GRISSOM")))
                .fetch().map(r -> CustomerModel.fromRecord(r));

        System.out.println(customers);
    }

    @Test
    public void noCondition() {
        String lastName
                = null;
//                = "SNYDER";
        List<CustomerModel> customers = dsl.select()
                .from(CUSTOMER)
                .where(
                        CUSTOMER.FIRST_NAME.eq("MARION")
                                .and(
                                        (lastName != null ? CUSTOMER.LAST_NAME.eq(lastName) : DSL.noCondition())
                                )
                )
                .fetch().map(r -> CustomerModel.fromRecord(r));

        System.out.println(customers);
    }

    @Test
    public void complexJoins() {
        List<FullFilmModel> fullFilms = dsl.select(
                        FILM.FILM_ID,
                        FILM.TITLE,
                        FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR,
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME)
                .from(FILM)
                .join(FILM_ACTOR)
                .on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(ACTOR)
                .on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .where(ACTOR.FIRST_NAME.eq("GENE"))
                .fetch()
                .stream()
                .map(r -> FullFilmModel.fromRecord(r))
                .collect(Collectors.toList());

        System.out.println(fullFilms);
    }
}
