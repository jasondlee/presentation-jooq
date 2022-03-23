package com.steeplesoft.jooq.codegen.model;

import java.time.LocalDateTime;

public class CountryModel {
    private Integer countryId;
    private String country;
    private LocalDateTime lastUpdate;

    public CountryModel() {

    }

    public CountryModel(Integer countryId, String country, LocalDateTime lastUpdate) {
        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
