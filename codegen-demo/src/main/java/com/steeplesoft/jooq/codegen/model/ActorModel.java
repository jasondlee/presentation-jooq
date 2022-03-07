package com.steeplesoft.jooq.codegen.model;

import org.jooq.Record;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;

public class ActorModel {
    private Integer id;
    private String lastName;
    private String firstName;

    public ActorModel() {

    }

    public ActorModel(Integer id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public ActorModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ActorModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ActorModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static ActorModel fromRecord(Record r) {
        return new ActorModel()
                .setId(r.getValue(ACTOR.ACTOR_ID))
                .setFirstName(r.getValue(ACTOR.FIRST_NAME))
                .setLastName(r.getValue(ACTOR.LAST_NAME));
    }
}
