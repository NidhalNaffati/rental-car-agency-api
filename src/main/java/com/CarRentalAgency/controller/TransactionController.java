package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.entity.TransactionRequest;
import com.CarRentalAgency.services.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;


    @PostMapping
    public Transaction makeTransaction(@Valid @RequestBody TransactionRequest request) {
        return transactionService.saveTransaction(request);
    }


    @GetMapping
    public List<Transaction> listAllTransactions() {
        return transactionService.listAllTransaction();
    }
}
