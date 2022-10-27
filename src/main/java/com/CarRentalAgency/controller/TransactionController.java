package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.services.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;


    @PostMapping("/{customerID}/{dealerID}/{carRegistrationNumber}")
    public Transaction makeTransaction(
            @PathVariable(name = "customerID") long customerID,
            @PathVariable(name = "dealerID") long dealerID,
            @PathVariable(name = "carRegistrationNumber") int carRegistranstionNumber) {

        return transactionService.makeTransaction(customerID, dealerID, carRegistranstionNumber);

    }


    @GetMapping("/list")
    public List<Transaction> listAllTransations() {
        return transactionService.listAllTransaction();
    }
}
