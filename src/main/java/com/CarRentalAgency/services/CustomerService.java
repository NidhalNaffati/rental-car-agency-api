package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.exception.NoSuchElementException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer) throws MethodArgumentNotValidException;

    List<Customer> findAllCustomers();

    void deleteCustomerById(Long id) throws java.util.NoSuchElementException;


    List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws java.util.NoSuchElementException;


    Optional<Customer> findCustomerById(Long id) throws NoSuchElementException;

    Optional<Customer> findCustomerByEmail(String email) throws NoSuchElementException;


    // TODO: 04/10/2022 i  should handel the null fields when i save or update.
    Customer updateCustomer(Long id, Customer newCustomer) throws NoSuchElementException, AlreadyExistsException;
}
