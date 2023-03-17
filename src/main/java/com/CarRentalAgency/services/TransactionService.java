package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.entity.TransactionRequest;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(TransactionRequest transaction);

    List<Transaction> listAllTransaction();
}
