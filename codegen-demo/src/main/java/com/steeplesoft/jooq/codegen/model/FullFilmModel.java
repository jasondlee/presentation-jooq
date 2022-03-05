package com.steeplesoft.jooq.codegen.model;

import org.jooq.Record;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;
import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;

public class FullFilmModel extends FilmModel {
    private ActorModel actorModel;

    public ActorModel getActor() {
        return actorModel;
    }

    public void setActor(ActorModel actorModel) {
        this.actorModel = actorModel;
    }

    public static FullFilmModel fromRecord(Record r) {
        FullFilmModel film = new FullFilmModel();
        film.setId(r.get(FILM.FILM_ID));
        film.setTitle(r.get(FILM.TITLE));
        film.setDescription(r.get(FILM.DESCRIPTION));
        film.setReleaseYear(r.get(FILM.RELEASE_YEAR));

        film.setActor(new ActorModel()
                .setId(r.get(ACTOR.ACTOR_ID))
                .setFirstName(r.get(ACTOR.FIRST_NAME))
                .setLastName(r.get(ACTOR.LAST_NAME)));

        return film;
    }
}
