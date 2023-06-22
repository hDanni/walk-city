package com.techelevator.dao;

import com.techelevator.model.Award;

import java.util.List;

public interface AwardDao {
    List<Award> getAllAwards();

    List<Award> getAwardById(int id);

}
