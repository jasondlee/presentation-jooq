package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Author {
    private Long id;
    private String lastName;
    private String firstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Author fromRecord(Record r) {
        Author author = new Author();
        author.setId(r.get("id", Long.class));
        author.setFirstName(r.get("first_name", String.class));
        author.setLastName(r.get("last_name", String.class));

        return author;
    }
}
