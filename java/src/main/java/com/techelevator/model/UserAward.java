package com.techelevator.model;

public class UserAward {

    private int userId;
    private int awardId;

    public UserAward(){}

    public UserAward(int userId, int awardId) {
        this.userId = userId;
        this.awardId = awardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }
}
