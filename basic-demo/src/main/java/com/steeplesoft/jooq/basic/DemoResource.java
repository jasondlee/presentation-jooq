package com.steeplesoft.jooq.basic;

import com.steeplesoft.jooq.basic.model.Actor;
import com.steeplesoft.jooq.basic.model.Film;
import com.steeplesoft.jooq.basic.model.FullFilm;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.jooq.Record7;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {
    private DSLContext dsl = getDslContext();

    @GET
    @Path("/actors")
    public List<Actor> getActors() {
        return dsl.select(
                        DSL.field("actor_id"),
                        DSL.field("last_name"),
                        DSL.field("first_name")
                )
                .from("actor")
                .fetch()
                .map(r -> Actor.fromRecord(r));
    }

    @GET
    @Path("/films")
    public List<Film> getFilms(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("year") Integer releaseYear
    ) {
        SelectConditionStep<Record4<Object, Object, Object, Object>> query = dsl.select(
                        DSL.field("film_id"),
                        DSL.field("title"),
                        DSL.field("description"),
                        DSL.field("release_year")
                )
                .from("film")
                .where("1=1");
        if (title != null) {
            query = query.and(DSL.field("title").like("%" + title + "%"));
        }
        if (description != null) {
            query = query.and(DSL.field("description").like("%" + description + "%"));
        }
        if (releaseYear != null) {
            query = query.and(DSL.field("release_year").eq(releaseYear));
        }
        System.out.println(query.getSQL());
        return query.fetch()
                .map(r -> Film.fromRecord(r));
    }

    @GET
    @Path("/films/{actor}")
    public List<FullFilm> getFilmsByActor(@PathParam("actor") @NotNull Integer actor) {
        SelectConditionStep<Record7<Object, Object, Object, Object, Object, Object, Object>> query = dsl.select(
                        DSL.field("film.film_id"),
                        DSL.field("film.title"),
                        DSL.field("film.description"),
                        DSL.field("film.release_year"),
                        DSL.field("actor.actor_id"),
                        DSL.field("actor.first_name"),
                        DSL.field("actor.last_name")
                )
                .from("film")
                .join("film_actor").on("film_actor.film_id = film.film_id")
                .join("actor").on("film_actor.actor_id = actor.actor_id")
                .where(DSL.field("actor.actor_id").eq(actor));

        return query.fetch()
                .map(r -> FullFilm.fromRecord(r));
    }

    @GET
    @Path("/filmsAndActors")
    public List<FullFilm> getFullFilms(@QueryParam("actor") String actor) {
        SelectConditionStep<Record7<Object, Object, Object, Object, Object, Object, Object>> query = dsl.select(
                        DSL.field("film.film_id"),
                        DSL.field("film.title"),
                        DSL.field("film.description"),
                        DSL.field("film.release_year"),
                        DSL.field("actor.actor_id"),
                        DSL.field("actor.first_name"),
                        DSL.field("actor.last_name")
                )
                .from("film")
                .join("film_actor").on("film_actor.film_id = film.film_id")
                .join("actor").on("film_actor.actor_id = actor.actor_id")
                .where("1=1");
        if (actor != null) {
            query = query.and("actor.last_name = ?", actor);
        }
        return query.fetch()
                .map(r -> FullFilm.fromRecord(r));
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
                            .withExecuteLogging(true)
                            .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED)
                    );

            return DSL.using(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
