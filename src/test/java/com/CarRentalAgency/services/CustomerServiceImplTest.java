package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

        underTestService = new CustomerServiceImpl(customerRepository);

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
    void testSaveCustomer_ShouldSuccess() {
        // resources: https://stackoverflow.com/questions/63526353/unit-test-for-save-method-of-jpa-repository-in-spring-boot

        //given
        Customer testCustomer = customer1;
        //when
        underTestService.saveCustomer(testCustomer);
        //then
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    void testSaveCustomer_ShouldThrowsAlreadyExistsException() {

        //given
        when(customerRepository.findCustomerByEmail(anyString()))
                .thenReturn(Optional.of(customer1));

        //when
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            underTestService.saveCustomer(customer1);
        });

        //then
        verify(customerRepository, times(1)).findCustomerByEmail(anyString());
        verify(customerRepository, times(0)).save(any());
        Assertions.assertEquals("THIS EMAIL:" + customer1.getEmail() + " ALREADY USED !!", exception.getMessage());
    }

    @Test
    void testDeleteCustomer_ShouldDeleteTheCustomer() {

        //given
        long existingCustomerID = customer1.getId();

        //when
        when(customerRepository.findById(existingCustomerID))
                .thenReturn(Optional.of(customer1));
        doNothing().when(customerRepository).deleteById(existingCustomerID);

        //then
        assertDoesNotThrow(() -> underTestService.deleteCustomerById(existingCustomerID));
        verify(customerRepository, times(1))
                .deleteById(existingCustomerID);
    }

    @Test
    void testDeleteCustomer_ShouldThrowNoSuchElementException() {

        //given
        Customer customer = customer1;
        long nonExistingCustomerID = customer.getId();
        final String expectedExceptionMessage =
                "CANNOT DELETE A NON-EXISTENT CUSTOMER WITH THIS ID: " + nonExistingCustomerID + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(";

        when(customerRepository.findById(nonExistingCustomerID))
                .thenReturn(Optional.empty());

        //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            underTestService.deleteCustomerById(nonExistingCustomerID);
        });

        //then
        verify(customerRepository, times(1)).findById(anyLong());
        verify(customerRepository, times(0)).deleteById(anyLong());
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testUpdateCustomer_ShouldSuccess() {
        // given
        Customer newCustomer = customer2;

        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerRepository.findCustomerById(1L)).thenReturn(customer1);
        when(customerRepository.save(customer1)).thenReturn(customer1);

        // when
        Customer updatedCustomer = underTestService.updateCustomer(1L, newCustomer);

        // then
        verify(customerRepository, times(1)).existsById(anyLong());
        verify(customerRepository, times(1)).save(any());

        assertNotNull(updatedCustomer);

        assertEquals(customer1.getId(), updatedCustomer.getId());
        assertEquals(newCustomer.getEmail(), updatedCustomer.getEmail());
        assertEquals(newCustomer.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(newCustomer.getLastName(), updatedCustomer.getLastName());
    }

    @Test
    void testUpdateCustomer_ShouldThrowNoSuchElementException() {
        // given
        long nonExistingId = 999_999L;
        final String expectedExceptionMessage = "user id: " + nonExistingId + " doesnt exist!!";
        when(customerRepository.existsById(nonExistingId))
                .thenReturn(false);

        // when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            underTestService.updateCustomer(nonExistingId, customer1);
        });

        // then
        verify(customerRepository, times(1)).existsById(anyLong());
        verify(customerRepository, times(0)).save(any());
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());

    }

    @Test
    void testUpdateCustomer_ShouldThrowAlreadyExistsException() {
        // given
        Long existingId = 1L;
        Customer newCustomer = customer2;
        String expectedExceptionMessage = "this email: " + newCustomer.getEmail() + " already exists !!";

        when(customerRepository.existsById(existingId)).thenReturn(true);
        when(customerRepository.findCustomerById(existingId)).thenReturn(customer1);
        when(customerRepository.findCustomerByEmail(newCustomer.getEmail())).thenReturn(Optional.of(customer2));

        // when
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class,
                () -> {
                    underTestService.updateCustomer(existingId, newCustomer);
                });

        // then
        verify(customerRepository, times(1)).existsById(anyLong());
        verify(customerRepository, times(0)).save(any());
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testFindAllCustomers_ShouldReturnAListOfCustomers() {

        //given
        List<Customer> customerList = Arrays.asList(customer1, customer2);

        //when
        when(customerRepository.findAll()).thenReturn(customerList);

        //then
        assertThat(underTestService.findAllCustomers())
                .contains(customer1, customer2)
                .size().isEqualTo(2);
    }

    @Test()
    void testFindCustomerById_ShouldReturnCustomer() {

        //given
        Customer existingCustomer = customer1;
        long id = existingCustomer.getId();

        //when
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));

        //then
        assertThat(underTestService.findCustomerById(id))
                .isEqualTo(existingCustomer);
    }

    @Test()
    void testFindById_ShouldThrowException() {

        //given
        Long wrongID = 999_999L;
        String expectedExceptionMessage = "THERE IS NO CUSTOMER WITH THIS ID: " + wrongID;

        //when
        when(customerRepository.findById(wrongID))
                .thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            underTestService.findCustomerById(wrongID);
        });

        //then
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> underTestService.findCustomerById(wrongID));

        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void testFindCustomerByEmail_ShouldReturnCustomer() {

        // given
        Customer existingCustomer = customer1;
        String email = existingCustomer.getEmail();


        // when
        when(customerRepository.findCustomerByEmail(email))
                .thenReturn(Optional.of(existingCustomer));


        // then
        assertThat(underTestService.findCustomerByEmail(email))
                .isEqualTo(existingCustomer);

        verify(customerRepository, times(1)).findCustomerByEmail(anyString());
    }


    @Test
    void testFindCustomerByEmail_ShouldThrowNoSuchElementException() {
        //given
        String email = "nonExistentEmail@gmail.com";
        String expectedExceptionMessage = "THE IS NO CUSTOMER WITH THIS EMAIL :" + email +
                                          " MAYBE YOU MEAN : [existentEmail@gmail.com, anotherExistentEmail@gmail.com]";

        when(customerRepository.findCustomerByEmail(email))
                .thenReturn(Optional.empty());

        when(customerRepository.approximateEmails(email))
                .thenReturn(List.of("existentEmail@gmail.com", "anotherExistentEmail@gmail.com"));

        //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> underTestService.findCustomerByEmail(email));

        //then
        assertEquals(expectedExceptionMessage, exception.getMessage());

    }


    @Test
    void testFindCustomerByFirstNameIgnoreCase_ShouldReturnCustomers() {
        //given
        String firstName = "myfirstname1";
        when(customerRepository.findByFirstNameIgnoreCase(firstName))
                .thenReturn(List.of(customer1));

        //when
        List<Customer> foundCustomers = underTestService.findCustomerByFirstNameIgnoreCase(firstName);

        //then
        assertThat(foundCustomers).containsExactly(customer1);
    }

    @Test
    void testFindCustomerByFirstNameIgnoreCase_ShouldThrowNoSuchElementException() {
        //given
        String firstName = "nonExistentName";
        String expectedExceptionMessage = "OOPS THIS CUSTOMER NAME DOESNT EXIST !! " +
                                          " MAYBE YOU MEAN : [existentName]";

        when(customerRepository.findByFirstNameIgnoreCase(firstName))
                .thenReturn(Collections.emptyList());

        when(customerRepository.approximateNames(firstName))
                .thenReturn(List.of("existentName"));

        //when
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> underTestService.findCustomerByFirstNameIgnoreCase(firstName));

        //then
        assertEquals(expectedExceptionMessage, exception.getMessage());
        verify(customerRepository).findByFirstNameIgnoreCase(firstName);
        verify(customerRepository).approximateNames(firstName);
    }

}
