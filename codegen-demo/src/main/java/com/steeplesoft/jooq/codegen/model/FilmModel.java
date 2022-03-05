package com.steeplesoft.jooq.codegen.model;

import org.jooq.Record;

import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;

public class FilmModel {
    private Integer id;
    private String title;
    private String description;
    private int releaseYear;

    public static FilmModel fromRecord(Record r) {
        return new FilmModel()
                .setId(r.get(FILM.FILM_ID))
                .setTitle(r.get(FILM.TITLE))
                .setDescription(r.get(FILM.DESCRIPTION))
                .setReleaseYear(r.get(FILM.RELEASE_YEAR));
    }

    public Integer getId() {
        return id;
    }

    public FilmModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FilmModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FilmModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public FilmModel setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }
}
