package com.techelevator.model;

public class Award {

    private int awardId;
    private int awardName;
//    private int awardDescription;
//    private int awardIcon;

    public Award(){}


    public Award(int awardId, int awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public int getAwardName() {
        return awardName;
    }

    public void setAwardName(int awardName) {
        this.awardName = awardName;
    }

    @Override
    public String toString() {
        return "Award{" +
                "awardId=" + awardId +
                ", awardName=" + awardName +
                '}';
    }
}
