package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import com.steeplesoft.jooq.codegen.model.FullFilmModel;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record7;
import org.jooq.SelectConditionStep;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;
import static com.steeplesoft.jooq_demo.generated.tables.FilmActor.FILM_ACTOR;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {
    @Inject
    private DSLContext dsl;

    @GET
    @Path("/actors")
    public List<ActorModel> getActors() {
        return dsl.select()
                .from(ACTOR)
                .fetch()
                .map(r -> ActorModel.fromRecord(r));
    }

    @GET
    @Path("/films")
    public List<FilmModel> getFilms(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("year") Integer releaseYear
    ) {
        SelectConditionStep<Record> query = dsl.select()
                .from(FILM)
                .where("1=1");
        if (title != null) {
            query = query.and(FILM.TITLE.like("%" + title + "%"));
        }
        if (description != null) {
            query = query.and(FILM.DESCRIPTION.like("%" + description + "%"));
        }
        if (releaseYear != null) {
            query = query.and(FILM.RELEASE_YEAR.eq(releaseYear));
        }
        return query.fetch().map(r -> FilmModel.fromRecord(r));
    }

    @GET
    @Path("/films/{actor}")
    public List<FullFilmModel> getFilmsByActor(@PathParam("actor") @NotNull Integer actor) {
        SelectConditionStep<Record7<Integer, String, String, Integer, Integer, String, String>> query = dsl.select(
                        FILM.FILM_ID,
                        FILM.TITLE,
                        FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR,
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME
                )
                .from(FILM)
                .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .where(ACTOR.ACTOR_ID.eq(actor));

        return query.fetch().stream()
                .map(r -> FullFilmModel.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/filmsAndActors")
    public List<FullFilmModel> getFullFilms(@QueryParam("actor") String actor) {
        SelectConditionStep<Record7<Integer, String, String, Integer, Integer, String, String>> query = dsl.select(
                        FILM.FILM_ID,
                        FILM.TITLE,
                        FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR,
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME
                )
                .from(FILM)
                .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .where("1=1");
        if (actor != null) {
            query = query.and(ACTOR.LAST_NAME.eq(actor));
        }
        return query.fetch().stream()
                .map(r -> FullFilmModel.fromRecord(r))
                .collect(Collectors.toList());
    }
}
