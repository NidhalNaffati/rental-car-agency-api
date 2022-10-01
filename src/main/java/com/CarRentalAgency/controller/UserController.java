package com.CarRentalAgency.controller;


import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.UserNotFoundException;
import com.CarRentalAgency.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user/list")
    public List<User> list() {
        List<User> listUsers = userService.findAll();
        return listUsers;
    }

    @GetMapping("user/list/name/{name}")
    public User fetchUserByName(@PathVariable("name") String userName) throws UserNotFoundException {
        User user = userService.findByFirstNameIgnoreCase(userName);
        return user;
    }


    @GetMapping("/user/list/id/{id}")
    public User fetchUserByID(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping("/user/save")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("user/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        // I should handel the wrong id from the user . ( EmptyResultDataAccessException )

        userService.deleteById(id);
        return "User Deleted Succesfuly ;) ";
    }

    @PutMapping("user/update/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody @Valid User user) throws UserNotFoundException {
        return userService.updateUser(id, user);
    }


}
