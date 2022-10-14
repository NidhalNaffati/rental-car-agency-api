package com.CarRentalAgency.controller;


import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/list")
    public List<User> list() {
        List<User> listUsers = userService.findAll();
        if(listUsers.isEmpty()) throw new NoSuchElementException("THERE IS NO USER IN THE DATA BASE.");
        return listUsers;
    }

    @GetMapping("/list/name/{name}")
    public List<User> fetchUserByName(@PathVariable("name") String userName) throws NoSuchElementException {
        List<User> userList = userService.findByFirstNameIgnoreCase(userName);
        return userList;
    }


    @GetMapping("/list/id/{id}")
    public User fetchUserByID(@PathVariable("id") Long id) throws NoSuchElementException {
        return userService.findById(id).get();
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user) throws NoSuchElementException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) throws NoSuchElementException {
        userService.deleteById(id);
        return "Deleted Successfully ;) ";
    }

    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody @Valid User user) throws NoSuchElementException {
        return userService.updateUser(id, user);
    }

}
