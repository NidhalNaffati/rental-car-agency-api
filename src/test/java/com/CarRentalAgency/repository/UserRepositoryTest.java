/*
package com.CarRentalAgency.repository;

import com.CarRentalAgency.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {UserRepository.class})
@Slf4j
@DataJdbcTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        User user = User.builder()
                .firstName("userRepository")
                .lastName("userRepository")
                .email("ryuke@userRepository.com")
                .build();

        entityManager.persist(user);
    }

    @Test
    public void whenFindById(){
        User user = userRepository.findById(1L).get();
        assertEquals(user.getFirstName(),"userRepository");
    }
}*/
