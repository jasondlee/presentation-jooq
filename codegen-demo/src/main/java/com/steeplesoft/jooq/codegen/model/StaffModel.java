package com.steeplesoft.jooq.codegen.model;

import java.time.LocalDateTime;

public class StaffModel extends BaseModel {
    protected Integer staffId;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected AddressModel address;
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

    public StaffModel setStaffId(Integer staffId) {
        this.staffId = staffId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StaffModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StaffModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public StaffModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public AddressModel getAddress() {
        return address;
    }

    public StaffModel setAddress(AddressModel address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StaffModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public StaffModel setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
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
