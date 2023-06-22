package com.techelevator.dao;

import com.techelevator.model.Award;
import com.techelevator.model.UserAward;

import java.util.List;

public interface UserAwardDao {

    List<UserAward> getAllUserAwardsByUserId(int id);

}
