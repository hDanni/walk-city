package com.techelevator.model;

public class CheckIn {

    private String businessId;
    private int locationId;
    private int userId;


    public CheckIn(){}

    public CheckIn(int locationId, int userId) {
        this.locationId = locationId;
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
