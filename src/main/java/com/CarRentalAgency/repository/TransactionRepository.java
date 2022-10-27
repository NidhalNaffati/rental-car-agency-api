package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
/*
    @Query("select T from Transaction T where " +
            "T.customer = ?1 " +
            "T.")
    Transaction makeTransaction(long customerID , long dealerID,  int registrationNumber);
*/

}
