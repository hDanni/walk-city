package com.techelevator.service;


import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.model.mapbox.Direction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WalkCITYService {

    private UserDao userDao;
    private CheckInDao checkInDao;
    private AwardDao awardDao;
    private DbAttractionDao dbAttractionDao;
    private UserAwardDao userAwardDao;

    public WalkCITYService(UserDao userDao, AwardDao awardDao, CheckInDao checkInDao, DbAttractionDao dbAttractionDao, UserAwardDao userAwardDao){
        this.userDao = userDao;
        this.awardDao = awardDao;
        this.checkInDao = checkInDao;
        this.dbAttractionDao = dbAttractionDao;
        this.userAwardDao = userAwardDao;
    }

/*    public Direction getAccountByUsername(String username){
        Direction account = null;

        account = accountDao.getAccountByUsername(username);

        return account;
    }

    public Direction getAccountByUserId(int userId){
        Direction account = null;

        account = accountDao.getAccountByUserId(userId);

        return account;

    }*/


    public List<User> getListOfUsers(){
        List<User> userList = new ArrayList<>();

        userList = userDao.findAll();

        return userList;
    }

    public List<Award> getAllAwards(){
        List<Award> awardList = new ArrayList<>();
        awardList = awardDao.getAllAwards();
        return  awardList;

    }

    //todo
    public List<Award> getAwardById(String username){
        List<Award> awardList = new ArrayList<>();
        int userIdByUsername = userDao.findIdByUsername(username);
        awardList = awardDao.getAwardById(userIdByUsername);
        return awardList;
    }

    public List<UserAward> getAllUserAwardsByUserId(int id){
        List<UserAward> userAwardList = new ArrayList<>();
        userAwardList = userAwardDao.getAllUserAwardsByUserId(id);
        return userAwardList;
    }

    public List<DbAttraction> getAllAttractions(){
        List<DbAttraction> attractionList = new ArrayList<>();
        attractionList = dbAttractionDao.getAllFeaturedLocations();
        return attractionList;
    }

    public List<DbAttraction> getAllAttractionsByCategory(String category){
        List<DbAttraction> attractionListByCategory = new ArrayList<>();
        attractionListByCategory = dbAttractionDao.getAttractionByCategory(category);
        return attractionListByCategory;
    }

    public List<DbAttraction> getAttractionByKeyword(String keyword){
        List<DbAttraction> attractionListByKeyword = new ArrayList<>();
        attractionListByKeyword = dbAttractionDao.getAttractionByKeyword(keyword);
        return attractionListByKeyword;
    }


    public boolean addedAttractionToDatabase(DbAttraction dbAttraction){
        boolean success = false;
        try {
        dbAttractionDao.createAttraction(dbAttraction);
        success = true;
        } catch(Exception e) {
        } return success;
    }


//    public boolean addedCheckInToDatabase(CheckIn checkin){
//        boolean success = false;
//        try {
//            checkInDao.createCheckIn(checkin);
//            success = true;
//        } catch(Exception e) {
//        } return success;
//    }

    public boolean addCheckIn(DbAttraction dbAttraction, String username){
        boolean success = false;
        try {
            System.out.println(username);
            int userId = userDao.findIdByUsername(username);
            List<DbAttraction> test = dbAttractionDao.getAllLocations();
            for (DbAttraction attraction : test) {
                if (attraction.getBusinessId().equals(dbAttraction.getBusinessId())) {
                    checkInDao.createCheckIn(dbAttraction.getBusinessId(), userId);
                }
            }
            dbAttractionDao.createAttraction(dbAttraction);
            checkInDao.createCheckIn(dbAttraction.getBusinessId(), userId);
        } catch(Exception e) {
        } return success;

    }


    public List<CheckIn> getAllCheckIns(){
        List<CheckIn> checkInList = new ArrayList<>();
        checkInList = checkInDao.getAllCheckIns();
        return checkInList;
    }

    public List<DbAttraction> getCheckInById(String username){
        List<DbAttraction> dbAttractionList = new ArrayList<>();
        int userId = userDao.findIdByUsername(username);
        dbAttractionList = dbAttractionDao.getCheckInById(userId);
        return dbAttractionList;
    }


//
//    public List<Transfer> getTransferByUsername(String username){
//        List<Transfer> transferList = new ArrayList<>();
//
//        transferList = transferDao.getTransfersByUsername(username);
//
//        return transferList;
//    }
//
//    public List<Transfer> getPendingTransfersByUsername(String username){
//        List<Transfer> transferList = new ArrayList<>();
//
//        transferList = transferDao.getPendingTransfersByUsername(username);
//
//        return transferList;
//    }
//
//    public Transfer getTransferByTransferId(int transferId) {
//        Transfer transferDetails = null;
//
//        transferDetails = transferDao.getTransferByTransferId(transferId);
//
//        return transferDetails;
//    }
//
//    public List<Transfer> getTransferByStatusId(int transferStatusId) {
//        List<Transfer> transferStatus = null;
//
//        transferStatus = transferDao.getTransferByStatusId(transferStatusId);
//
//        return transferStatus;
//    }
//    public List<Transfer> getTransferByTypeId(int transferTypeId) {
//        List<Transfer> transferType = null;
//
//        transferType = transferDao.getTransferByTypeId(transferTypeId);
//
//        return transferType;
//    }
//
//    public void updateTransferStatus(Transfer transfer) {
//        Account accountFrom = accountDao.getAccountByAccountId(transfer.getAccountFrom());
//        Account accountTo = accountDao.getAccountByAccountId(transfer.getAccountTo());
//        if (transfer.getTransferStatusId() == 2) {
//            int balanceCheck = transfer.getAmount().compareTo(accountFrom.getBalance());
//            if (balanceCheck == 1) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
//            } else {
//                accountDao.decreaseAccountBalance(accountFrom, transfer.getAmount());
//                accountDao.increaseAccountBalance(accountTo, transfer.getAmount());
//                transferDao.update(transfer, transfer.getTransferStatusId());
//            }
//        }   if (transfer.getTransferStatusId() == 3) {
//            transferDao.update(transfer, transfer.getTransferStatusId());
//        }
//    }
//
//    // should take in an account to take from and account to transfer to
//    public void transfer(Transfer transfer){
//
//        Account accountFrom = accountDao.getAccountByAccountId(transfer.getAccountFrom());
//        Account accountTo = accountDao.getAccountByAccountId(transfer.getAccountTo());
//        // add exception where if transfer.getAmount() > accountFrom.getBalance
//        // if balanceCheck evaluates to 1 then transfer.getAmount is greater than accountFrom.getBalance
//        int balanceCheck = transfer.getAmount().compareTo(accountFrom.getBalance());
//        int positiveCheck = transfer.getAmount().compareTo(BigDecimal.ZERO);
//        // add exception where if accountFrom
//        boolean accountCheck = accountFrom.getAccountId() == accountTo.getAccountId();
//
//        // request check
//        if (transfer.getTransferTypeId() == 1){
//            if (accountCheck){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't request money from yourself, silly");
//            } else if (positiveCheck == 0 || positiveCheck == -1){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't request zero or negative dollars");
//            } else {
//                transferDao.createTransfer(transfer);
//            }
//        }
//        // send check
//        if (transfer.getTransferTypeId() == 2) {
//            if (balanceCheck == 1 ){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
//            } else if (accountCheck){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't send money to yourself, silly");
//            } else if (positiveCheck == 0 || positiveCheck == -1){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't send zero or negative dollars");
//            } else {
//                accountDao.decreaseAccountBalance(accountFrom, transfer.getAmount());
//                accountDao.increaseAccountBalance(accountTo, transfer.getAmount());
//                transferDao.createTransfer(transfer);
//            }
//        }
//    }
}
