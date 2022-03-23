package com.steeplesoft.jooq.codegen.mapper;

import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;

import com.steeplesoft.jooq.codegen.model.ActorModel;
import com.steeplesoft.jooq_demo.generated.tables.records.ActorRecord;
import org.jooq.RecordMapper;

public class ActorMapper implements RecordMapper<ActorRecord, ActorModel> {
    @Override
    public ActorModel map(ActorRecord actorRecord) {
        return new ActorModel()
                .setId(actorRecord.getValue(ACTOR.ACTOR_ID))
                .setFirstName(actorRecord.getValue(ACTOR.FIRST_NAME))
                .setLastName(actorRecord.getValue(ACTOR.LAST_NAME));
    }
}
