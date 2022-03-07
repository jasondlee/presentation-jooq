package com.steeplesoft.jooq.codegen.model;

public class CityModel {
    private Integer cityId;
    private String city;

    public CityModel(Integer cityId, String city) {
        this.cityId = cityId;
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
