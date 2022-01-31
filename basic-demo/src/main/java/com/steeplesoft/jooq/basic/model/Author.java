package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Author {
    private Long id;
    private String lastName;
    private String firstName;

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static Author fromRecord(Record r) {
        Author author = new Author()
                .setId(r.getValue("id", Long.class))
                .setFirstName(r.getValue("first_name", String.class))
                .setLastName(r.getValue("last_name", String.class));

        return author;
    }
}
