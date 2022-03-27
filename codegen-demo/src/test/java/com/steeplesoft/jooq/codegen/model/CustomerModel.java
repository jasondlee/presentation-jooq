package com.steeplesoft.jooq.codegen.model;

import org.jooq.Record;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.steeplesoft.jooq_demo.generated.tables.Customer.CUSTOMER;

public class CustomerModel extends BaseModel {
    protected Integer customerId;
    protected Integer storeId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected AddressModel address;
    protected Boolean activeBool;
    protected LocalDate createDate;
    protected LocalDateTime lastUpdate;
    protected Integer active;

    public CustomerModel() {

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public CustomerModel setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public CustomerModel setStoreId(Integer storeId) {
        this.storeId = storeId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressModel getAddress() {
        return address;
    }

    public CustomerModel setAddress(AddressModel address) {
        this.address = address;
        return this;
    }

    public Boolean getActiveBool() {
        return activeBool;
    }

    public CustomerModel setActiveBool(Boolean activeBool) {
        this.activeBool = activeBool;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public CustomerModel setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public CustomerModel setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public CustomerModel setActive(Integer active) {
        this.active = active;
        return this;
    }

    public static CustomerModel fromRecord(Record r) {
        return new CustomerModel()
                .setCustomerId(r.get(CUSTOMER.CUSTOMER_ID))
                .setStoreId(r.get(CUSTOMER.STORE_ID))
                .setFirstName(r.get(CUSTOMER.FIRST_NAME))
                .setLastName(r.get(CUSTOMER.LAST_NAME))
                .setEmail(r.get(CUSTOMER.EMAIL))
//                .setAddressId(r.get(CUSTOMER.ADDRESS_ID))
                .setActiveBool(r.get(CUSTOMER.ACTIVEBOOL))
                .setCreateDate(r.get(CUSTOMER.CREATE_DATE))
                .setLastUpdate(r.get(CUSTOMER.LAST_UPDATE))
                .setActive(r.get(CUSTOMER.ACTIVE))
                ;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "customerId=" + customerId +
                ", storeId=" + storeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", activeBool=" + activeBool +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", active=" + active +
                "}\n";
    }
}
