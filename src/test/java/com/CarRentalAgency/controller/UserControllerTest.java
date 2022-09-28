/*
package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstName("nidhal")
                .lastName("naffati")
                .email("email@email.com")
                .id(1L)
                .build();

        Mockito.when(userService.saveUser(user))
                .thenReturn(user);
    }

    @Test
    void fetchUserByID() {

    }

    @Test
    void saveUser() {
        User inputUser = User.builder()
                .firstName("nidhal")
                .lastName("naffati")
                .email("email@email.com")
                .build();

        Mockito.when(userService.saveUser(user))
                .thenReturn(inputUser);

        mockMvc.perform(MockMvcRequestBuilders.post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")

    }
}*/
