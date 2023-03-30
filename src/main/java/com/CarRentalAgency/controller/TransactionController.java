package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.entity.TransactionRequest;
import com.CarRentalAgency.services.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;


    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.saveTransaction(request);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        List<Transaction> transactions = transactionService.listAllTransaction();
        return ResponseEntity.ok(transactions);
    }
}
