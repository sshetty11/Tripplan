package com.shilpashetty.Tripplan.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
 
public class TripPlanConfig extends Configuration {
    @NotEmpty
    private String version;
 
    @JsonProperty
    public String getVersion() {
        return version;
    }
 
    @JsonProperty
    public void setVersion(String version) {
        this.version = version;
    }
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}