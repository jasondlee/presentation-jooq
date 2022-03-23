package com.steeplesoft.jooq.codegen.mapper;

import static com.steeplesoft.jooq_demo.generated.tables.Film.FILM;

import com.steeplesoft.jooq.codegen.model.FilmModel;
import com.steeplesoft.jooq_demo.generated.tables.records.FilmRecord;
import org.jooq.RecordMapper;

public class FilmMapper implements RecordMapper<FilmRecord, FilmModel> {
    @Override
    public
    FilmModel map(FilmRecord filmRecord) {
        System.out.println("FilmMapper");
        return new FilmModel()
                .setId(filmRecord.get(FILM.FILM_ID))
                .setTitle(filmRecord.get(FILM.TITLE))
                .setDescription(filmRecord.get(FILM.DESCRIPTION))
                .setReleaseYear(filmRecord.get(FILM.RELEASE_YEAR));
    }
}
