package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.NoSuchElementException;

public interface CustomerService {
    Customer saveCustomer(Customer customer) throws MethodArgumentNotValidException;

    List<Customer> findAllCustomers();

    void deleteCustomerById(Long id) throws NoSuchElementException;

    List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws NoSuchElementException;

    Customer findCustomerById(Long id) throws NoSuchElementException;

    Customer findCustomerByEmail(String email) throws NoSuchElementException;

    Customer updateCustomer(Long id, Customer newCustomer) throws NoSuchElementException, AlreadyExistsException;
}
