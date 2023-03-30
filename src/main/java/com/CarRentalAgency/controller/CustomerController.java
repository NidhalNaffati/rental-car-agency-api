package com.CarRentalAgency.controller;


import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.services.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer newCustomer) {
        Customer updatedCustomer = customerService.updateCustomer(id, newCustomer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Customer customer = customerService.findCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name) {
        List<Customer> customers = customerService.findCustomerByFirstNameIgnoreCase(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}