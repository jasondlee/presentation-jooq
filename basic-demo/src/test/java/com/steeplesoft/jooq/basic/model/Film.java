package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Film {
    protected Long id;
    protected String title;
    protected String description;
    protected int releaseYear;

    public static Film fromRecord(Record r) {
        return new Film()
                .setId(r.get("film_id", Long.class))
                .setTitle(r.get("title", String.class))
                .setDescription(r.get("description", String.class))
                .setReleaseYear(r.get("release_year", Integer.class));
    }

    public Long getId() {
        return id;
    }

    public Film setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Film setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Film setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Film setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
