package com.steeplesoft.jooq.basic.model;

import org.jooq.Record;

public class Actor {
    private Long id;
    private String lastName;
    private String firstName;

    public Long getId() {
        return id;
    }

    public Actor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Actor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Actor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static Actor fromRecord(Record r) {
        return new Actor()
                .setId(r.getValue("actor_id", Long.class))
                .setFirstName(r.getValue("first_name", String.class))
                .setLastName(r.getValue("last_name", String.class));
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
