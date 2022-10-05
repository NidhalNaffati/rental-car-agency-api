package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User saveUser(User user) throws AlreadyExistsException, NotFoundException;

    Optional<User> findById(Long id) throws NotFoundException;

    Optional<User> findUserByEmail(String email) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    User updateUser(Long id, User user) throws NotFoundException;

    List<User> findByFirstNameIgnoreCase(String userName) throws NotFoundException;

}
