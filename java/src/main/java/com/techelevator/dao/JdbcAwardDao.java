package com.techelevator.dao;

import com.techelevator.model.Award;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAwardDao implements AwardDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAwardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Award> getAllAwards() {
        List<Award> awards = new ArrayList<>();
        String query = "SELECT * FROM award;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query);
        while(results.next()) {
            Award awardResult = mapRowToAward(results);
            awards.add(awardResult);
        }
        return awards;
    }

    @Override
    public List<Award> getAwardById(int id) {
        List<Award> awards = new ArrayList<>();
        String query = "SELECT * FROM user_award WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(query, id);
        while(results.next()) {
            Award awardResult = mapRowToAward(results);
            awards.add(awardResult);
        }
        return awards;
    }


//    @Override
//    public List<Award> getAwardByUsername(String username){
//        List<Award> checkins = new ArrayList<>();
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
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
//
//        while (results.next()){
//            checkins.add(mapRowToAward(results));
//        }
//        return checkins;
//    }


    public Award createAward(Award award) {
        String query = "INSERT INTO public.user_award(user_id, award_id) VALUES (?, ?);";
        jdbcTemplate.update(query, award.getAwardId());
        return award;
    }



    private Award mapRowToAward(SqlRowSet results) {
        Award award = new Award();
        award.setAwardId(results.getInt("award_id"));
        award.setAwardName(results.getInt("award_name"));
        return award;
    }
}

