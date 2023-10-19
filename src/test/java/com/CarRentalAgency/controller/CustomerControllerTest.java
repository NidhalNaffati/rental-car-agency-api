package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.services.CustomerServiceImpl;
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

class CustomerControllerTest {

  @Mock
  private CustomerServiceImpl customerService;

  @InjectMocks
  private CustomerController customerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createCustomer_shouldReturnCreatedStatus() {
    Customer customer = new Customer();
    when(customerService.saveCustomer(customer)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.createCustomer(customer);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(customer, response.getBody());
  }

  @Test
  void updateCustomer_shouldReturnUpdatedCustomer() {
    Long id = 1L;
    Customer newCustomer = new Customer();
    Customer updatedCustomer = new Customer();
    when(customerService.updateCustomer(id, newCustomer)).thenReturn(updatedCustomer);

    ResponseEntity<Customer> response = customerController.updateCustomer(id, newCustomer);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedCustomer, response.getBody());
  }

  @Test
  void deleteCustomer_shouldReturnNoContentStatus() {
    Long id = 1L;

    ResponseEntity<Void> response = customerController.deleteCustomer(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(customerService, times(1)).deleteCustomerById(id);
  }

  @Test
  void getCustomerById_shouldReturnCustomer() {
    Long id = 1L;
    Customer customer = new Customer();
    when(customerService.findCustomerById(id)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.getCustomerById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());
  }

  @Test
  void getAllCustomers_shouldReturnListOfCustomers() {
    List<Customer> customers = new ArrayList<>();
    when(customerService.findAllCustomers()).thenReturn(customers);

    ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customers, response.getBody());
  }

  @Test
  void getCustomerByEmail_shouldReturnCustomer() {
    String email = "test@example.com";
    Customer customer = new Customer();
    when(customerService.findCustomerByEmail(email)).thenReturn(customer);

    ResponseEntity<Customer> response = customerController.getCustomerByEmail(email);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customer, response.getBody());
  }

  @Test
  void getCustomerByName_shouldReturnListOfCustomers() {
    String name = "John";
    List<Customer> customers = new ArrayList<>();
    when(customerService.findCustomerByFirstNameIgnoreCase(name)).thenReturn(customers);

    ResponseEntity<List<Customer>> response = customerController.getCustomerByName(name);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(customers, response.getBody());
  }
}