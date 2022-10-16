package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Customer;

import com.CarRentalAgency.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl userService;

    private Customer customer;


    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .firstName("nidhal")
                .lastName("naffati")
                .email("email@email.com")
                .id(1L)
                .build();

        Mockito.when(userService.saveCustomer(customer))
                .thenReturn(customer);
    }

    @Test
    void fetchUserByID() throws Exception {
        Mockito.when(userService.findCustomerById(1L))
                .thenReturn(Optional.ofNullable(customer));

        mockMvc.perform(get("/api/v1/user/list/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.firstName")
                        .value(customer.getFirstName()));
    }

    @Test
    void saveUser() throws Exception {
        Customer inputCustomer = Customer.builder()
                .firstName("ryuke")
                .lastName("testcase")
                .email("test@gmail.com")
                .build();

        Mockito.when(userService.saveCustomer(inputCustomer))
                .thenReturn(customer);

        mockMvc.perform(post("/api/v1/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"email\": \"test@gmail.com\",\n" +
                                "        \"firstName\": \"ryuke\",\n" +
                                "        \"lastName\": \"testcase\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }
}
