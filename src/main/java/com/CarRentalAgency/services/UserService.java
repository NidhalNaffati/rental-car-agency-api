package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.AlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User saveUser(User user) throws AlreadyExistsException, NoSuchElementException;

    Optional<User> findById(Long id) throws NoSuchElementException;

    Optional<User> findUserByEmail(String email) throws NoSuchElementException;

    void deleteById(Long id) throws NoSuchElementException;

    User updateUser(Long id, User user) throws NoSuchElementException;

    List<User> findByFirstNameIgnoreCase(String userName) throws NoSuchElementException;

}
