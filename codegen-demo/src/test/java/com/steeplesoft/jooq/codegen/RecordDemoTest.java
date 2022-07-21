package com.steeplesoft.jooq.codegen;

import com.steeplesoft.jooq_demo.generated.Keys;
import com.steeplesoft.jooq_demo.generated.tables.records.ActorRecord;
import com.steeplesoft.jooq_demo.generated.tables.records.StaffRecord;
import com.steeplesoft.jooq_demo.generated.tables.records.StoreRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.steeplesoft.jooq_demo.generated.Tables.STORE;
import static com.steeplesoft.jooq_demo.generated.tables.Actor.ACTOR;

public class RecordDemoTest {
    private DSLContext dsl = DslContextProvider.getDslContext();

    @Test
    public void create() {
        ActorRecord record = dsl.newRecord(ACTOR);
        record.setFirstName("Test");
        record.setLastName("Actor");
        System.out.println(record);
        record.store();
        int storeId = record.getActorId();
        System.out.println(record);
        System.out.println("New ID: " + storeId);
    }

    @Test
    public void update() {
        ActorRecord actor = dsl.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(10));
        actor.setLastName("Updated");
        actor.update();

        ActorRecord actor2 = dsl.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(10));
        Assertions.assertEquals("Updated", actor2.getLastName());
    }

    @Test
    public void delete() {
        ActorRecord record = dsl.newRecord(ACTOR);
        record.setFirstName("Test");
        record.setLastName("Actor");
        System.out.println(record);
        record.store();
        int storeId = record.getActorId();

        record.delete();

        ActorRecord record2 = dsl.fetchOne(ACTOR, ACTOR.ACTOR_ID.eq(storeId));
        Assertions.assertNull(record2);
    }

    @Test
    public void childRecords() {
        StoreRecord record = dsl.fetchOne(STORE, STORE.STORE_ID.eq(1));
        Result<StaffRecord> staffRecord = record.fetchChildren(Keys.STAFF__STAFF_STORE_ID_FKEY);
        System.out.println(staffRecord);
    }
}
