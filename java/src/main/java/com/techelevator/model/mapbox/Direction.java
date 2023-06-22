package com.techelevator.model.mapbox;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Direction {

    public ArrayList<Routes> routes;

    public ArrayList<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Routes> routes) {
        this.routes = routes;
    }
}
