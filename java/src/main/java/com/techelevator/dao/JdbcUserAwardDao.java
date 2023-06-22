package com.techelevator.dao;

import com.techelevator.model.Award;
import com.techelevator.model.UserAward;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


    @Component
    public class JdbcUserAwardDao implements UserAwardDao {

        private final JdbcTemplate jdbcTemplate;

        public JdbcUserAwardDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


        @Override
        public List<UserAward> getAllUserAwardsByUserId(int id) {
            List<UserAward> userAwards = new ArrayList<>();
            String query = "SELECT * FROM user_award WHERE user_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, id);
            while(results.next()) {
                UserAward userAwardResult = mapRowToUserAward(results);
                userAwards.add(userAwardResult);
            }
            return userAwards;
        }

        private UserAward mapRowToUserAward(SqlRowSet results) {
            UserAward userAward = new UserAward();
            userAward.setUserId(results.getInt("user_id"));
            userAward.setAwardId(results.getInt("award_id"));
            return userAward;
        }


    }
