package com.steeplesoft.jooq.codegen.model;

import com.steeplesoft.jooq_demo.generated.tables.records.StaffRecord;

import java.time.LocalDateTime;

import static com.steeplesoft.jooq_demo.generated.tables.Store.STORE;

public class StaffModel {
    private Integer staffId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private LocalDateTime lastUpdate;

    public StaffModel() {
    }

    public StaffModel(Integer staffId, String firstName, String lastName, String userName, String email, LocalDateTime lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.lastUpdate = lastUpdate;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
//    public static StaffModel fromRecord(StaffRecord r) {
//        STORE.staff().STAFF_ID,
//                STORE.staff().FIRST_NAME,
//                STORE.staff().LAST_NAME,
//                STORE.staff().USERNAME,
//                STORE.staff().EMAIL,
//                STORE.staff().LAST_UPDATE
//    }
}
