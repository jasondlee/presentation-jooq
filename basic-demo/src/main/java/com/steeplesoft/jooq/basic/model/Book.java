package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Book {
    private Long id;
    private String title;
    private String description;
    private int publishYear;

    public static Book fromRecord(Record r) {
        return new Book()
                .setId(r.get("id", Long.class))
                .setTitle(r.get("title", String.class))
                .setDescription(r.get("description", String.class))
                .setPublishYear(r.get("published_year", Integer.class));
    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Book setPublishYear(int publishYear) {
        this.publishYear = publishYear;
        return this;
    }
}
