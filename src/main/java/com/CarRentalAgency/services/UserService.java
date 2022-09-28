package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User saveUser(User user);

    User findById(Long id) throws UserNotFoundException;

    void deleteById(Long id);

    User updateUser(Long id, User user);

    List<User> findByFirstNameIgnoreCase(String userName);

}
