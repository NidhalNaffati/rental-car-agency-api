package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .firstName("myName")
                .lastName("myLastName")
                .email("myEmail@myCompany.com")
                .build();

        entityManager.persist(customer);
    }

    @Test
    @DisplayName("find by id test ")
    public void whenFindById() {
        customer = customerRepository.findById(1L).get();
        assertEquals(customer.getFirstName(), "myName");
    }
}
