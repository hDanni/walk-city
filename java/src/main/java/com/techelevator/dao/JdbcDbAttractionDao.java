package com.techelevator.dao;

import com.techelevator.model.Attraction;
import com.techelevator.model.CheckIn;
import com.techelevator.model.DbAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDbAttractionDao implements DbAttractionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDbAttractionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<DbAttraction> getAllFeaturedLocations() {
        List<DbAttraction> locations = new ArrayList<>();
        //todo
        String query = "SELECT * FROM location WHERE featured = true;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query);
        while(results.next()) {
            DbAttraction attractionResult = mapRowToAttraction(results);
            locations.add(attractionResult);
        }
        return locations;
    }

    @Override
    public List<DbAttraction> getAllLocations() {
        List<DbAttraction> locations = new ArrayList<>();
        //todo
        String query = "SELECT * FROM location;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query);
        while(results.next()) {
            DbAttraction attractionResult = mapRowToAttraction(results);
            locations.add(attractionResult);
        }
        return locations;
    }

    @Override
    public List<DbAttraction> getAttractionByBusiId(String businessId) {
        List<DbAttraction> locations = new ArrayList<>();

        String query = "SELECT * FROM location WHERE business_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query, businessId);
        if(results.next()) {
            locations.add(mapRowToAttraction(results));
//            return mapRowToAttraction(results);
        }
        System.out.println("No transfer exists with the id " + businessId);
        return null;
    }

    @Override
    public List<DbAttraction> getAttractionByCategory(String category) {
        List<DbAttraction> locations = new ArrayList<>();

        String query = "SELECT * FROM location WHERE category = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query, category);
        if(results.next()) {
            locations.add(mapRowToAttraction(results));
//            return mapRowToAttraction(results);
        }
        System.out.println("No attraction exists with the id " + category);
        return locations;
    }

    public DbAttraction createAttraction(DbAttraction dbattraction){
    String query = "INSERT INTO public.location(\n" +
            "\tname, business_id, lat, \"long\", address, category, description, image_url, featured)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, false);";
        jdbcTemplate.update(query, dbattraction.getName(), dbattraction.getBusinessId(),
                dbattraction.getLatitude(), dbattraction.getLongitude(), dbattraction.getAddress(), dbattraction.getCategory(),
                dbattraction.getDescription(), dbattraction.getImageUrl());
        return dbattraction;
    }


    @Override
    public List<DbAttraction> getCheckInById(int id) {
        List<DbAttraction> checkIns = new ArrayList<>();
        String query = "SELECT location_id, business_id, name, lat, long, address, category, description, featured, image_url " +
                "FROM vw_checkin_history WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query, id);
        while(results.next()) {
            DbAttraction checkInResult = mapRowToAttraction(results);
            checkIns.add(checkInResult);
        }
        return checkIns;
//        System.out.println("No user exists with the id " + id);
//        return null;
    }


    @Override
    public List<DbAttraction> getAttractionByKeyword(String keyword){
        List<DbAttraction> locations = new ArrayList<>();
        String query = "SELECT * FROM location WHERE UPPER (name) LIKE UPPER (?) OR UPPER (address) LIKE UPPER (?) OR UPPER (description) LIKE UPPER (?) OR UPPER (category) LIKE UPPER (?)";
        String searchKeyword = "%" + keyword + "%";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query, searchKeyword, searchKeyword, searchKeyword, searchKeyword);
        while(results.next()) {
            DbAttraction checkInResult = mapRowToAttraction(results);
            locations.add(checkInResult);
        }
        return locations;
    }


//
//
//    @Override
//    public List<Attraction> getLocationByName(String name){
//        List<Attraction> locations = new ArrayList<>();
//
//        String sql ="SELECT " +
//                "transfer_id, transfer_type_id,transfer_status_id, " +
//                "account_from,username_from, " +
//                "account_to,username_to, " +
//                "amount " +
//                "FROM " +
//                "vw_transfer_details " +
//                "WHERE " +
//                "? IN (username_from, username_to)";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
//
//        while (results.next()){
//            locations.add(mapRowToLocation(results));
//        }
//        return locations;
//    }
//
//
//    public Attraction createLocation(Attraction location) {
//        String query = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)"
//                +  " VALUES (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(query, award.getTransferTypeId(), transfer.getTransferStatusId(),
//                transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
//        return location;
//    }







    private DbAttraction mapRowToAttraction(SqlRowSet results) {
        DbAttraction result = new DbAttraction();
        result.setLocationId(results.getString("location_id"));
        result.setName(results.getString("name"));
        result.setBusinessId(results.getString("business_id"));
        result.setAddress(results.getString("address"));
        result.setLatitude(results.getDouble("lat"));
        result.setLongitude(results.getDouble("long"));
        result.setCategory(results.getString("category"));
        result.setDescription(results.getString("description"));
        result.setImageUrl(results.getString("image_url"));
        result.setFeatured(results.getBoolean("featured"));

        return result;
    }
}
