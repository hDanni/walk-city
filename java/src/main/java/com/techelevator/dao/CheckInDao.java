package com.techelevator.dao;

import com.techelevator.model.CheckIn;
import com.techelevator.model.DbAttraction;

import java.util.List;

public interface CheckInDao {
    List<CheckIn> getAllCheckIns();

//    List<DbAttraction> getCheckInById(int id);

    boolean createCheckIn(String businessId, int userId);
}
