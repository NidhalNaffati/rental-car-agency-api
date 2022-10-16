package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> findAllCustomers();

    void deleteCustomerById(Long id) throws NoSuchElementException;


    List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws NoSuchElementException;


    Optional<Customer> findCustomerById(Long id) throws com.CarRentalAgency.exception.NoSuchElementException;

    Optional<Customer> findCustomerByEmail(String email) throws com.CarRentalAgency.exception.NoSuchElementException;


    // TODO: 04/10/2022 i  should handel the null fields when i save or update.
    Customer updateCustomer(Long id, Customer newCustomer) throws com.CarRentalAgency.exception.NoSuchElementException, AlreadyExistsException;
}
