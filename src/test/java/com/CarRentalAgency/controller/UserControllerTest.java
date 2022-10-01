package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.User;

import com.CarRentalAgency.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

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
    void fetchUserByID() throws Exception {
        Mockito.when(userService.findById(1L))
                .thenReturn(user);

        mockMvc.perform(get("/user/list/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.firstName").value(user.getFirstName()));
    }

    @Test
    void saveUser() throws Exception {
        User inputUser = User.builder()
                .firstName("ryuke")
                .lastName("testcase")
                .email("test@gmail.com")
                .build();

        Mockito.when(userService.saveUser(inputUser))
                .thenReturn(user);

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"email\": \"test@gmail.com\",\n" +
                                "        \"firstName\": \"ryuke\",\n" +
                                "        \"lastName\": \"testcase\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }
}
