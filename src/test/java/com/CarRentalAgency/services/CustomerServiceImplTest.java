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


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    /**
     * @InjectMocks creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
     */
    @InjectMocks
    private CustomerServiceImpl underTestService;


    /**
     * @Mock creates a mock implementation for the classes you need.
     */
    @Mock
    private CustomerRepository customerRepository;

    private Customer customer1;

    private Customer customer2;

    List<Customer> customerList;


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


    @Test
    void saveCustomer() {
        // resources: https://stackoverflow.com/questions/63526353/unit-test-for-save-method-of-jpa-repository-in-spring-boot

        //given
        Customer testCustomer = customer1;
        //when
        underTestService.saveCustomer(testCustomer);
        //then
        verify(customerRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Find All Customers in the DB")
    void findAll_GivenAListAsParam_ExpectingTheSameList() {

        List<Customer> customerList = Arrays.asList(customer1, customer2);

        given(customerRepository.findAll())
                .willReturn(customerList);

        assertThat(underTestService.findAllCustomers())
                .isEqualTo(Arrays.asList(customer1, customer2));

        assertThat(underTestService.findAllCustomers())
                .hasSize(2)
                .contains(customer1, customer2);
    }

    @Test()
    void findCustomerById_ShouldReturnCustomer() {

        // finding an existing Customer by his ID.
        given(customerRepository.findById(1L))
                .willReturn(Optional.ofNullable(customer1));

        assertThat(underTestService.findCustomerById(1L))
                .isEqualTo(Optional.ofNullable(customer1));

    }

    @Test()
    void findById_givenWrongId_ShouldThrowException() {

        Long wrongID = 999_999L;

        // finding a non-existing Customer by his email.

        //this will throw NoSuchElementException.
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> underTestService.findCustomerById(wrongID))
                .withMessage("THERE IS NO CUSTOMER WITH THIS ID: 999999");

    }

    @Test
    void findCustomerByEmail_ShouldReturnARecord_OrThrowException() {

        // finding an existing Customer by his email.
        given(customerRepository.findCustomerByEmail("myEmail1@gmail.com"))
                .willReturn(Optional.ofNullable(customer1));

        assertThat(underTestService.findCustomerByEmail("myEmail1@gmail.com"))
                .isEqualTo(Optional.ofNullable(customer1));

    }


    @Test
    void findCustomerByEmail_ShouldThrowException() {

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> underTestService.findCustomerByEmail("thisEmailTotalyUnFoundInTheDB"));
    }


    @Test
    void findCustomerByFirstName_ShouldRetunRecord() {
        //given
        final String firstName = "fname";
        final String reversedFirstName = "Fname";

        customer1.setFirstName(firstName);

        customerList = Arrays.asList(customer1);

        //when
        when(customerRepository
                .findByFirstNameIgnoreCase(firstName)).thenReturn(customerList);

        //then

        assertThat(underTestService.
                findCustomerByFirstNameIgnoreCase(reversedFirstName))
                .contains(customer1)
                .hasSize(1);

    }


    @Test
    void deletingCustomer_retrievesCustomerByID() {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        underTestService.deleteCustomerById(1L);

        verify(customerRepository, times(1)).deleteById(eq(1L));

    }


    @Test
    void deletingCustomer_ShouldThrowNoSuchElementException() {

        // resources: https://stackoverflow.com/questions/69109025/how-do-you-assert-with-delete-method-void

        final String OUTPUT_MESSAGE = "CANNOT DELETE NON-EXISTING CUSTOMER, THIS ID: 1 DOESNT EXIST OR HE IS ALREADY DELETED.";

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> underTestService.deleteCustomerById(1L))
                .withMessage(OUTPUT_MESSAGE);


    }

}
