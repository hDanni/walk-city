package com.techelevator.dao;

import com.techelevator.model.CheckIn;
import com.techelevator.model.DbAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcCheckInDao implements CheckInDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCheckInDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<CheckIn> getAllCheckIns() {
        List<CheckIn> checkins = new ArrayList<>();
        String query = "SELECT * FROM check_in_history;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query);
        while(results.next()) {
            CheckIn checkInResult = mapRowToCheckIn(results);
            checkins.add(checkInResult);
        }
        return checkins;
    }

//todo moved to JdbcDbAttractionDao

//    @Override
//    public List<DbAttraction> getCheckInById(int id) {
//        List<DbAttraction> checkIns = new ArrayList<>();
//        String query = "SELECT location_id, business_id, name, lat, long, address, category, description, featured, image_url " +
//                "FROM vw_checkin_history WHERE user_id = ?";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(query, id);
//        while(results.next()) {
//            DbAttraction checkInResult = mapRowToCheckIn(results);
//            checkIns.add(checkInResult);
//        }
//        return checkIns;
////        System.out.println("No user exists with the id " + id);
////        return null;
//    }



    public boolean createCheckIn(String businessId, int userId) {
        boolean success = false;
        try {
            String query = "INSERT INTO public.check_in_history (business_id, user_id) VALUES (?, ?);";
            jdbcTemplate.update(query, businessId, userId);
            success = true;
        } catch(Exception e){
        }
        return success;
    }


    private CheckIn mapRowToCheckIn(SqlRowSet results) {
        CheckIn checkin = new CheckIn();
        checkin.setLocationId(results.getInt("location_id"));
        checkin.setUserId(results.getInt("user_id"));
        return checkin;
    }

}
