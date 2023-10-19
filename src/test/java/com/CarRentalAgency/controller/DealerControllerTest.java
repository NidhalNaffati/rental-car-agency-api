package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.services.DealerServiceImpl;
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

class DealerControllerTest {

  @Mock
  private DealerServiceImpl dealerService;

  @InjectMocks
  private DealerController dealerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveDealer_shouldReturnCreatedStatus() {
    Dealer dealer = new Dealer();
    when(dealerService.saveDealer(dealer)).thenReturn(dealer);

    ResponseEntity<Dealer> response = dealerController.saveDealer(dealer);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(dealer, response.getBody());
  }

  @Test
  void updateDealer_shouldReturnUpdatedDealer() {
    Long id = 1L;
    Dealer newDealer = new Dealer();
    Dealer updatedDealer = new Dealer();
    when(dealerService.updateDealer(id, newDealer)).thenReturn(updatedDealer);

    ResponseEntity<Dealer> response = dealerController.updateDealer(id, newDealer);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedDealer, response.getBody());
  }

  @Test
  void deleteDealerById_shouldReturnNoContentStatus() {
    Long id = 1L;

    ResponseEntity<Void> response = dealerController.deleteDealerById(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(dealerService, times(1)).deleteDealerById(id);
  }

  @Test
  void findDealerById_shouldReturnDealer() {
    Long id = 1L;
    Dealer dealer = new Dealer();
    when(dealerService.findDealerById(id)).thenReturn(dealer);

    ResponseEntity<Dealer> response = dealerController.findDealerById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(dealer, response.getBody());
  }

  @Test
  void findAllDealers_shouldReturnListOfDealers() {
    List<Dealer> dealers = new ArrayList<>();
    when(dealerService.findAllDealers()).thenReturn(dealers);

    ResponseEntity<List<Dealer>> response = dealerController.findAllDealers();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(dealers, response.getBody());
  }

  @Test
  void findDealerByName_shouldReturnListOfDealers() {
    String name = "John";
    List<Dealer> dealers = new ArrayList<>();
    when(dealerService.findDealerByFirstNameIgnoreCase(name)).thenReturn(dealers);

    ResponseEntity<List<Dealer>> response = dealerController.findDealerByName(name);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(dealers, response.getBody());
  }

  @Test
  void findDealerByEmail_shouldReturnDealer() {
    String email = "test@example.com";
    Dealer dealer = new Dealer();
    when(dealerService.findDealerByEmail(email)).thenReturn(dealer);

    ResponseEntity<Dealer> response = dealerController.findDealerByEmail(email);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(dealer, response.getBody());
  }
}