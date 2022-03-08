package com.steeplesoft.jooq.codegen.model;

import java.time.LocalDateTime;

public class CityModel {
    private Integer cityId;
    private String city;
    private LocalDateTime lastUpdate;

    public CityModel() {

    }

    public CityModel(Integer cityId, String city, LocalDateTime lastUpdate) {
        this.cityId = cityId;
        this.city = city;
        this.lastUpdate = lastUpdate;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
