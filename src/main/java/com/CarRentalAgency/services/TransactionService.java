package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Transaction;

import java.util.List;

public interface TransactionService {
     Transaction makeTransaction(long customerID, long dealerID, int registrationNumber) ;

     List<Transaction> listAllTransaction();
}
