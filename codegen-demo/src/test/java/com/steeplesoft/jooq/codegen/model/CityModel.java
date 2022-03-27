package com.steeplesoft.jooq.codegen.model;

import java.time.LocalDateTime;

public class CityModel extends BaseModel {
    protected Integer cityId;
    protected String city;
    protected LocalDateTime lastUpdate;

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

    public CityModel setCityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CityModel setCity(String city) {
        this.city = city;
        return this;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public CityModel setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "cityId=" + cityId +
                ", city='" + city + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
