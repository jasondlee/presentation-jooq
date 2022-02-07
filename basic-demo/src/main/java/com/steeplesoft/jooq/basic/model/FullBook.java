package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class FullBook extends Book {
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static FullBook fromRecord(Record r) {
        FullBook book = new FullBook();
        book.setId(r.get("books.id", Long.class));
        book.setTitle(r.get("books.title", String.class));
        book.setDescription(r.get("books.description", String.class));
        book.setPublishYear(r.get("books.published_year", Integer.class));

        book.setAuthor(new Author()
                .setId(r.get("authors.id", Long.class))
                .setFirstName(r.get("authors.first_name", String.class))
                .setLastName(r.get("authors.last_name", String.class)));

        return book;
    }
}
