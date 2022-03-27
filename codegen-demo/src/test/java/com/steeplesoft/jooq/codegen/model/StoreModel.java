package com.steeplesoft.jooq.codegen.model;

import java.util.ArrayList;
import java.util.List;

public class StoreModel extends BaseModel {
    protected Integer storeId;
    protected AddressModel address;
    protected List<StaffModel> staff = new ArrayList<>();;

    public StoreModel() {
        System.out.println("");
    }

    public StoreModel(Integer storeId, AddressModel address, List<StaffModel> staff) {
        this.storeId = storeId;
        this.address = address;
        this.staff = staff;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public StoreModel setStoreId(Integer storeId) {
        this.storeId = storeId;
        return this;
    }

    public AddressModel getAddress() {
        return address;
    }

    public StoreModel setAddress(AddressModel address) {
        this.address = address;
        return this;
    }

    public List<StaffModel> getStaff() {
        return staff;
    }

    public StoreModel setStaff(List<StaffModel> staff) {
        this.staff = staff;
        return this;
    }

    @Override
    public String toString() {
        return "StoreModel{" +
                "storeId=" + storeId +
                ", address=" + address +
                ", staff=" + staff +
                '}';
    }
}
