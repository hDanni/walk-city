package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Location {

    @JsonProperty("display_address")
    public ArrayList<String> display_address;

    public Location() {
    }

    public ArrayList<String> getDisplay_address() {
        return display_address;
    }

    public void setDisplay_address(ArrayList<String> display_address) {
        this.display_address = display_address;
    }
}
