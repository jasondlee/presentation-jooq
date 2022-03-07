package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq.codegen.model.FilmModel;
import com.steeplesoft.jooq.codegen.model.FullFilmModel;
import org.jooq.Condition;
import org.jooq.DSLContext;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;
import static com.steeplesoft.jooq_demo.generated.tables.FilmActor.FILM_ACTOR;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Demo1Resource {
    @Inject
    private DSLContext dsl;

    @GET
    @Path("/actors")
    public List<ActorModel> getActors() {
        return dsl.fetch(ACTOR)
                .map(r -> ActorModel.fromRecord(r));
    }

    @GET
    @Path("/films")
    public List<FilmModel> getFilms(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("year") Integer releaseYear
    ) {
        List<Condition> conditions = new ArrayList<>();
        if (title != null) {
            conditions.add(FILM.TITLE.like("%" + title + "%"));
        }
        if (description != null) {
            conditions.add(FILM.DESCRIPTION.like("%" + description + "%"));
        }
        if (releaseYear != null) {
            conditions.add(FILM.RELEASE_YEAR.eq(releaseYear));
        }

        return dsl.select()
                .from(FILM)
                .where(conditions)
                .fetch().map(r -> FilmModel.fromRecord(r));
    }

    @GET
    @Path("/films/{actor}")
    public List<FullFilmModel> getFilmsByActor(@PathParam("actor") @NotNull Integer actor) {
        return dsl.select(
                        FILM.FILM_ID,
                        FILM.TITLE,
                        FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR,
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME)
                .from(FILM)
                .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .where(ACTOR.ACTOR_ID.eq(actor))
                .fetch().stream()
                .map(r -> FullFilmModel.fromRecord(r))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/filmsAndActors")
    public List<FullFilmModel> getFullFilms(@QueryParam("actor") String actor) {
        List<Condition> conditions = new ArrayList<>();
        if (actor != null) {
            conditions.add(ACTOR.LAST_NAME.eq(actor));
        }

        return dsl.select(
                        FILM.FILM_ID,
                        FILM.TITLE,
                        FILM.DESCRIPTION,
                        FILM.RELEASE_YEAR,
                        ACTOR.ACTOR_ID,
                        ACTOR.FIRST_NAME,
                        ACTOR.LAST_NAME)
                .from(FILM)
                .join(FILM_ACTOR).on(FILM_ACTOR.FILM_ID.eq(FILM.FILM_ID))
                .join(ACTOR).on(FILM_ACTOR.ACTOR_ID.eq(ACTOR.ACTOR_ID))
                .where(conditions)
                .fetch().stream()
                .map(r -> FullFilmModel.fromRecord(r))
                .collect(Collectors.toList());
    }
}
