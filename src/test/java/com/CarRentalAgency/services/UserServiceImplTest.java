package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService ;

    @MockBean
    private UserRepository userRepository ;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .firstName("ryuke")
                .lastName("nidhal")
                .email("ryuke@nidhal.com")
                .build();

        Mockito.when(userRepository.findUserByEmailContaining("ryuke"))
                .thenReturn(Optional.ofNullable(user));
    }

    @Test
    @DisplayName("Get Name from User Table.")
    void FindUserByFirstName() {
        String userName="ryuke";
        User userFound = userService.findUserByEmailContaining(userName).get();

        assertEquals(userName,userFound.getFirstName());
    }

}