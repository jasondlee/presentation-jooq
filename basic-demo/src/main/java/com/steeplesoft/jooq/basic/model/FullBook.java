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
        
        Author author = new Author();
        author.setId(r.get("authors.id", Long.class));
        author.setFirstName(r.get("authors.first_name", String.class));
        author.setLastName(r.get("authors.last_name", String.class));


        book.setAuthor(author);

        return book;
    }
}
