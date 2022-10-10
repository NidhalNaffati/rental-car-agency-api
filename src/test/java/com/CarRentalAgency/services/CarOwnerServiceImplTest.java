package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.repository.CarOwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class CarOwnerServiceImplTest {

    @TestConfiguration
    static class CarOwnerServiceImplTestContextConfiguration {
        @Bean
        public CarOwnerServiceImpl carOwnerService() {
            return new CarOwnerServiceImpl();
        }
    }

    @Autowired
    CarOwnerServiceImpl carOwnerService;

    @MockBean
    CarOwnerRepository carOwnerRepository;

    @BeforeEach
    void setUp() {

        List<Car> carList = new ArrayList<>();

        CarOwner carOwner1 = CarOwner.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .carList(carList)
                .build();

        CarOwner carOwner2 = CarOwner.builder()
                .id(2L)
                .email("myEmail2@gmail.com")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .carList(carList)
                .build();

        List<CarOwner> carOwnerList = Arrays.asList(carOwner1, carOwner2);

        Mockito.when(carOwnerRepository.findAll())
                .thenReturn(carOwnerList);
    }


    @Test
    void findAll_GivenAListAsParam_ExpectingTheSameList() {

        List<Car> carList = new ArrayList<>();

        CarOwner carOwner1 = CarOwner.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .carList(carList)
                .build();

        CarOwner carOwner2 = CarOwner.builder()
                .id(2L)
                .email("myEmail2@gmail.com")
                .firstName("myFirstName2")
                .lastName("myLastName2")
                .carList(carList)
                .build();

        List<CarOwner> carOwnerList = Arrays.asList(carOwner1, carOwner2);


        given(carOwnerRepository.findAll())
                .willReturn(carOwnerList);

        assertThat(carOwnerService.findAll())
                .isEqualTo(Arrays.asList(carOwner1,carOwner2));

        assertThat(carOwnerService.findAll())
                .hasSize(2)
                .contains(carOwner1, carOwner2);
    }


    @Test()
    void findById_ShouldReturnCarOwner_OrThrowException() {

         /*
            In this method i tested finding a record by ID ( carOwnerRepository.findById() )
            i tested a existing ID & non-existing ID
         */

        List<Car> carList = new ArrayList<>();

        CarOwner carOwner1 = CarOwner.builder()
                .id(1L)
                .email("myEmail1@gmail.com")
                .firstName("myFirstName1")
                .lastName("myLastName1")
                .carList(carList)
                .build();


        given(carOwnerRepository.findById(1L))
                .willReturn(Optional.ofNullable(carOwner1));

        assertThat(carOwnerService.findById(1L))
                .isEqualTo(carOwner1);


        given(carOwnerRepository.findById(10L))
                .willThrow(new NoSuchElementException());


        //this will throw NoSuchElementException
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            carOwnerService.findById(10L);
        });

    }

    @Test
    void findByEmail_ShouldReturnARecord_OrThrowException(){

    }

    @Test
    void addCarOwner_IfExists_OrThrowException(){

    }

    @Test
    void deletingCarOwner_IfExists_OrThrowException(){

    }

}
