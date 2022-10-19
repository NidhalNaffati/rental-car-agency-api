package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.CarRentalAgency.exception.NoSuchElementException;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl buyerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer1;

    private Customer customer2;


    @BeforeEach
    void setUp() {

        customer1 = Customer.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .build();

        customer2 = Customer.builder()
                .id(2L)
                .email("myEmail2@gmail.com")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .build();
    }

    // TODO: 14/10/2022 should be refactored.
    @Test
    @DisplayName("Find All Users in the DB")
    void findAll_GivenAListAsParam_ExpectingTheSameList() {

        List<Customer> customerList = Arrays.asList(customer1, customer2);


        given(customerRepository.findAll())
                .willReturn(customerList);

        assertThat(buyerService.findAllCustomers())
                .isEqualTo(Arrays.asList(customer1, customer2));

        assertThat(buyerService.findAllCustomers())
                .hasSize(2)
                .contains(customer1, customer2);
    }

    @Test()
    void findUserById_ShouldReturnUser_OrThrowException() {

        // finding an existing user by his ID.
        given(customerRepository.findById(1L))
                .willReturn(Optional.ofNullable(customer1));

        assertThat(buyerService.findCustomerById(1L))
                .isEqualTo(Optional.ofNullable(customer1));

    }
    @Test()
    void findById_givenWrongId_ShouldThrowException() {

        Long wrongID = 999_999L ;

        // finding a non-existing user by his email.
        given(customerRepository.findById(wrongID))
                .willThrow(NoSuchElementException.class);

        //this will throw NoSuchElementException.
        assertThrows(NoSuchElementException.class,
                () -> buyerService.findCustomerById(wrongID)
        );
    }

    @Test
    void findUserByEmail_ShouldReturnARecord_OrThrowException() {

        // finding an existing user by his email.
        given(customerRepository.findCustomerByEmail("myEmail1@gmail.com"))
                .willReturn(Optional.ofNullable(customer1));

        assertThat(buyerService.findCustomerByEmail("myEmail1@gmail.com"))
                .isEqualTo(Optional.ofNullable(customer1));

    }


    @Test
    void addUser_IfExists_OrThrowException() throws MethodArgumentNotValidException {

        //    List<Customer> userList = Arrays.asList(user);

        given(customerRepository.save(customer1))
                .willReturn(customer1);

        assertThat(buyerService.saveCustomer(customer1))
                .isEqualTo(customer1);
    }

    // TODO: 14/10/2022 should be completed. 
    @Test
    void deletingUser_IfExists_OrThrowException() {


        Customer customer3 = Customer.builder()
                .id(3L)
                .email("myEmail3@gmail.com")
                .firstName("myFirstName3")
                .lastName("myLastName3")
                .build();


        List<Customer> customerList = Arrays.asList(customer1, customer2, customer3);

    }

}
