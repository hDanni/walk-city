package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Hours {

    @JsonProperty("open")
    private Open[] open;


    public Open[] getOpen() { return open; }
    public void setOpen(Open[] value) { this.open = value; }


}


