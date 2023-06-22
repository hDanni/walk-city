package com.techelevator.service;


//@Component
//public class LocationService {
//
//    private LocationDao locationDao;
//
////todo all below needs to change lol
//    public LocationService(AccountDao accountDao, UserDao userDao, TransferDao transferDao){
//        this.accountDao = accountDao;
//        this.userDao = userDao;
//        this.transferDao = transferDao;
//    }
//
//    public Account getAccountByUsername(String username){
//        Account account = null;
//
//        account = accountDao.getAccountByUsername(username);
//
//        return account;
//    }
//
//    public Account getAccountByUserId(int userId){
//        Account account = null;
//
//        account = accountDao.getAccountByUserId(userId);
//
//        return account;
//
//    }
//
//    public User getUsernameByAccountId(int accountId){
//        User user = null;
//
//        user = userDao.findUsernameByAccountId(accountId);
//
//        return user;
//    }
//
//    public List<User> getListOfUsers(){
//        List<User> userList = new ArrayList<>();
//
//        userList = userDao.findAll();
//
//        return userList;
//    }
//
//    public List<Transfer> getAllTransfers(){
//        List<Transfer> transferList = new ArrayList<>();
//        transferList = transferDao.getAllTransfers();
//        return  transferList;
//
//    }
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
//}