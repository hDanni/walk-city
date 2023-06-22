package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Businesses {
    @JsonProperty("businesses")
    private ArrayList<Attraction> businesses;

    public ArrayList<Attraction> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(ArrayList<Attraction> businesses) {
        this.businesses = businesses;
    }
}
