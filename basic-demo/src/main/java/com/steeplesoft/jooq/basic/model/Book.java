package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Book {
    private Long id;
    private String title;
    private String description;
    private int publishYear;

    public static Book fromRecord(Record r) {
        Book book = new Book();
        book.setId(r.get("id", Long.class));
        book.setTitle(r.get("title", String.class));
        book.setDescription(r.get("description", String.class));
        book.setPublishYear(r.get("published_year", Integer.class));

        return book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
