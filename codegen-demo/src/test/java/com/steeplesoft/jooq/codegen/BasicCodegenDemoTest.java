package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import com.steeplesoft.jooq.codegen.model.FullFilmModel;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;
import static com.steeplesoft.jooq_demo.generated.tables.FilmActor.FILM_ACTOR;

public class BasicCodegenDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void getActors() {
        List<ActorModel> actors = dsl.fetch(ACTOR)
                .map(r -> ActorModel.fromRecord(r));

        System.out.println(actors);
    }

    @Test
    public void getFilms() {
        List<FilmModel> films = dsl.select()
                .from(FILM)
                .where(FILM.TITLE.like("%THE%"))
                .fetch().map(r -> FilmModel.fromRecord(r));

        System.out.println(films);
    }

    @Test
    public void getFilmsByActor() {
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
