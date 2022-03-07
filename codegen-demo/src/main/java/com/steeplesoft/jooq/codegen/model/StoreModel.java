package com.steeplesoft.jooq.codegen.model;

import java.util.ArrayList;
import java.util.List;

public class StoreModel {
    private Integer storeId;
    private AddressModel address;
    private List<StaffModel> staff = new ArrayList<>();;

    public StoreModel() {
        System.out.println("");
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public List<StaffModel> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffModel> staff) {
        this.staff = staff;
    }

    public StoreModel(Integer storeId, AddressModel address, List<StaffModel> staff) {
        this.storeId = storeId;
        this.address = address;
        this.staff = staff;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
