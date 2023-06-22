package com.techelevator.dao;

import com.techelevator.model.Attraction;
import com.techelevator.model.DbAttraction;

import java.util.List;

public interface DbAttractionDao {

    List<DbAttraction> getAllLocations();

    List<DbAttraction> getAllFeaturedLocations();

    List<DbAttraction> getAttractionByBusiId(String businessId);

    List<DbAttraction> getAttractionByCategory(String category);

    DbAttraction createAttraction(DbAttraction dbAttraction);

    List<DbAttraction> getCheckInById(int id);

    List<DbAttraction> getAttractionByKeyword(String keyword);

    }

