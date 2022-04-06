package com.steeplesoft.jooq.codegen.model;

import java.time.LocalDateTime;

public class AddressModel extends BaseModel {
    protected Integer addressId;
    protected String address;
    protected String address2;
    protected String district;
    protected CityModel city;
    protected String postalCode;
    protected String phone;
    protected LocalDateTime lastUpdate;

    public AddressModel() {

    }

    public AddressModel(Integer addressId, String address, String address2,
                        String district, CityModel city,
                        String postalCode, String phone, LocalDateTime lastUpdate) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public AddressModel setAddressId(Integer addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddressModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public AddressModel setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AddressModel setDistrict(String district) {
        this.district = district;
        return this;
    }

    public CityModel getCity() {
        return city;
    }

    public AddressModel setCity(CityModel city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressModel setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AddressModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public AddressModel setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "addressId=" + addressId +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", district='" + district + '\'' +
                ", city=" + city +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}