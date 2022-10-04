package com.CarRentalAgency.controller;


import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.exception.UserNotFoundException;
import com.CarRentalAgency.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/list")
    public List<User> list() {
        List<User> listUsers = userService.findAll();
        if(listUsers.isEmpty()) throw new NoSuchElementException("THERE IS NO USER IN THE DATA BASE.");
        return listUsers;
    }

    @GetMapping("/list/name/{name}")
    public List<User> fetchUserByName(@PathVariable("name") String userName) throws UserNotFoundException {
        List<User> userList = userService.findByFirstNameIgnoreCase(userName);
        return userList;
    }


    @GetMapping("/list/id/{id}")
    public User fetchUserByID(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.findById(id).get();
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user) throws UserNotFoundException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) throws NoSuchElementException {
        userService.deleteById(id);
        return "User Deleted Succesfuly ;) ";
    }

    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody @Valid User user) throws UserNotFoundException {
        return userService.updateUser(id, user);
    }


}
