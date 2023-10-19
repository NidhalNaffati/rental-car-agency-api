package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Transaction;
import com.CarRentalAgency.entity.TransactionRequest;
import com.CarRentalAgency.services.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

  @Mock
  private TransactionServiceImpl transactionService;

  @InjectMocks
  private TransactionController transactionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void makeTransaction_shouldReturnCreatedStatus() {
    TransactionRequest request = new TransactionRequest(1,2,3);
    Transaction transaction = new Transaction();
    when(transactionService.saveTransaction(request)).thenReturn(transaction);

    ResponseEntity<Transaction> response = transactionController.makeTransaction(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(transaction, response.getBody());
  }

  @Test
  void listAllTransactions_shouldReturnListOfTransactions() {
    List<Transaction> transactions = new ArrayList<>();
    when(transactionService.listAllTransaction()).thenReturn(transactions);

    ResponseEntity<List<Transaction>> response = transactionController.listAllTransactions();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(transactions, response.getBody());
  }
}