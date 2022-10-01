package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.*;

/*@AutoConfigureMockMvc

@Slf4j
@SpringBootTest*/
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@SpringBootTest
@ContextConfiguration(classes = {UserRepository.class})
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .firstName("myName")
                .lastName("myLastName")
                .email("myEmail@myCompany.com")
                .build();

        entityManager.persist(user);
    }

    @Test
    @DisplayName("find by id test ")
    public void whenFindById() {
        user = userRepository.findById(1L).get();
        assertEquals(user.getFirstName(), "myName");
    }
}
