package com.techelevator.model;

import java.util.ArrayList;

public class RootTest {

    public String name;
    public Location location;
    public Coordinate coordinates;
    public ArrayList<Hours> hours;
    public Open open;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Hours> getHours() {
        return hours;
    }

    public void setHours(ArrayList<Hours> hours) {
        this.hours = hours;
    }

    public Open getOpen() {
        return open;
    }

    public void setOpen(Open open) {
        this.open = open;
    }
}
