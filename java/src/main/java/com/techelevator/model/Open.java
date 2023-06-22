package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Open {

    public boolean is_overnight;
    public String start;
    public String end;
    public int day;

    public Open() {
    }

    public boolean isIs_overnight() {
        return is_overnight;
    }

    public void setIs_overnight(boolean is_overnight) {
        this.is_overnight = is_overnight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
