package com.steeplesoft.jooq.codegen.model;

import com.steeplesoft.jooq_demo.generated.tables.records.StaffRecord;

import java.time.LocalDateTime;

import static com.steeplesoft.jooq_demo.generated.tables.Store.STORE;

public class StaffModel {
    protected Integer staffId;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String email;
    protected LocalDateTime lastUpdate;

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

    @Override
    public String toString() {
        return "StaffModel{" +
                "staffId=" + staffId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
