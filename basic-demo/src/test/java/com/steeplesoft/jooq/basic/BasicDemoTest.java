package com.steeplesoft.jooq.basic;

import com.steeplesoft.jooq.basic.model.Actor;
import com.steeplesoft.jooq.basic.model.Film;
import com.steeplesoft.jooq.basic.model.FullFilm;
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

public class BasicDemoTest {
    private DSLContext dsl = getDslContext();

    @Test
    public void getActors() {
        List<Actor> actors = dsl.select(
                        DSL.field("actor_id"),
                        DSL.field("last_name"),
                        DSL.field("first_name")
                )
                .from(DSL.table("actor"))
                .fetch()
                .map(r -> Actor.fromRecord(r));
        System.out.println(actors);
    }

    @Test
    public void getFilms() {
        List<Film> films = dsl.select(
                        DSL.field("film_id"),
                        DSL.field("title"),
                        DSL.field("description"),
                        DSL.field("release_year")
                )
                .from(DSL.table("film"))
                .where(DSL.field("title").like("%THE%"))
                .fetch()
                .map(r -> Film.fromRecord(r));

        System.out.println(films);
    }

    @Test
    public void getFilmsByActor() {
        List<FullFilm> fullFilms = dsl.select(
                        DSL.field("film.film_id"),
                        DSL.field("film.title"),
                        DSL.field("film.description"),
                        DSL.field("film.release_year"),
                        DSL.field("actor.actor_id"),
                        DSL.field("actor.first_name"),
                        DSL.field("actor.last_name")
                )
                .from(DSL.table("film"))
                .join(DSL.table("film_actor")).on("film_actor.film_id = film.film_id")
                .join(DSL.table("actor")).on("film_actor.actor_id = actor.actor_id")
                .where(
                        DSL.field("actor.first_name")
                                .eq("GENE")
                )
                .fetch()
                .map(r -> FullFilm.fromRecord(r));

        System.out.println(fullFilms);
    }

    private DSLContext getDslContext() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/jooq_demo",
                    "jooq_demo", "jooq_demo");

            Configuration configuration = new DefaultConfiguration()
                    .set(conn)
                    .set(SQLDialect.POSTGRES)
                    .set(new Settings()
                            .withExecuteLogging(true)
                            .withRenderCatalog(false)
                            .withRenderSchema(false)
                            .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
                    );

            return DSL.using(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
