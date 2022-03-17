package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class FullFilm extends Film {
    private Actor actor;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public static FullFilm fromRecord(Record r) {
        FullFilm film = new FullFilm();
        film.setId(r.get("film.film_id", Long.class));
        film.setTitle(r.get("film.title", String.class));
        film.setDescription(r.get("film.description", String.class));
        film.setReleaseYear(r.get("film.release_year", Integer.class));

        film.setActor(new Actor()
                .setId(r.get("actor.actor_id", Long.class))
                .setFirstName(r.get("actor.first_name", String.class))
                .setLastName(r.get("actor.last_name", String.class)));

        return film;
    }

    @Override
    public String toString() {
        return "FullFilm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", actor=" + actor +
                '}';
    }
}
