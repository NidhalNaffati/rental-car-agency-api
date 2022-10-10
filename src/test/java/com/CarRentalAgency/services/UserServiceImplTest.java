package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.CarRentalAgency.exception.NoSuchElementException;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;


    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserServiceImpl userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("Find All Users in the DB")
    void findAll_GivenAListAsParam_ExpectingTheSameList() {

        User user1 = User.builder()
                .id(1L)
                .email("myFirstName1")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("myFirstName2")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .build();

        List<User> userList = Arrays.asList(user1, user2);


        given(userRepository.findAll())
                .willReturn(userList);

        assertThat(userRepository.findAll())
                .isEqualTo(Arrays.asList(user1, user2));

        assertThat(userRepository.findAll())
                .hasSize(2)
                .contains(user1, user2);
    }

    @Test()
    void findUserById_ShouldReturnUser_OrThrowException() {

        User user1 = User.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .build();


        // finding an existing user by his ID.
        given(userRepository.findById(1L))
                .willReturn(Optional.ofNullable(user1));

        assertThat(userService.findById(1L))
                .isEqualTo(Optional.ofNullable(user1));


        // finding a non-existing user by his ID.
        given(userRepository.findById(10L))
                .willThrow(NoSuchElementException.class);

        //this will throw NoSuchElementException.
        assertThrows(NoSuchElementException.class,
                () -> {
                    assertThat(userService.findById(10L))
                            .isEqualTo(Optional.ofNullable(user1));
                }
        );

    }

    @Test
    void findUserByEmail_ShouldReturnARecord_OrThrowException() {
        User user = User.builder()
                .email("myEmail@gmail.com")
                .firstName("myFirstName")
                .lastName("myLastName")
                .build();

        // finding an existing user by his email.
        given(userRepository.findUserByEmail("myEmail@gmail.com"))
                .willReturn(Optional.ofNullable(user));

        assertThat(userService.findUserByEmail("myEmail@gmail.com"))
                .isEqualTo(Optional.ofNullable(user));

        // finding a non-existing user by his email.
        given(userRepository.findUserByEmail("myEmail@gmail.com"))
                .willThrow(NoSuchElementException.class);

        //this will throw NoSuchElementException.
        assertThrows(NoSuchElementException.class,
                () -> {
                    assertThat(userService.findUserByEmail("thisIsNotMyEmail@gmail.com"))
                            .isEqualTo(Optional.ofNullable(user));
                }
        );
    }

    @Test
    void addUser_IfExists_OrThrowException() {

        User user = User.builder()
                .id(1L)
                .email("myEmail@gmail.com")
                .firstName("myFirstName")
                .lastName("myLastName")
                .build();

        //    List<User> userList = Arrays.asList(user);

        given(userRepository.save(user))
                .willReturn(user);

        assertThat(userService.saveUser(user))
                .isEqualTo(user);
    }

    @Test
    void deletingUser_IfExists_OrThrowException() {

        User user1 = User.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("myEmail2@gmail.com")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .build();

        User user3 = User.builder()
                .id(3L)
                .email("myEmail3@gmail.com")
                .firstName("myFirstName3")
                .lastName("myLastName3")
                .build();


        List<User> userList = Arrays.asList(user1, user2, user3);

    }

}
