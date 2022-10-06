package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.repository.CarOwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
class CarOwnerServiceImplTest {

    // TODO: 06/10/2022 this should be fixes by tomorrow.
    /* 6. Mocking With @MockBean
        https://www.baeldung.com/spring-boot-testing#:~:text=To%20achieve%20this%2C%20we%20can,provided%20by%20Spring%20Boot%20Test.&text=To%20check%20the%20Service%20class,configuration%20using%20the%20%40TestConfiguration%20annotation.
     */

    @TestConfiguration
    static class CarOwnerServiceImplTestContextConfiguration {
        @Bean
        public CarOwnerService carOwnerService() {
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

        CarOwner carOwner1 = new CarOwner(
                1L,
                "myEmail@gmail.com",
                "myFirstName",
                "myLastName",
                carList);

        CarOwner carOwner2 = new CarOwner(
                1L,
                "myEmail@gmail.com",
                "myFirstName",
                "myLastName",
                carList);

        List<CarOwner> carOwnerList = List.of(carOwner1, carOwner2);

        Mockito.when(carOwnerRepository.findAll())
                .thenReturn(carOwnerList);
    }


    @Test
    void findAll() {
        List<Car> carList = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(
                1L,
                "myEmail@gmail.com",
                "myFirstName",
                "myLastName",
                carList);

        CarOwner carOwner2 = new CarOwner(
                1L,
                "myEmail@gmail.com",
                "myFirstName",
                "myLastName",
                carList);

        List<CarOwner> carOwnerList = List.of(carOwner1, carOwner2);

        List<CarOwner> expectedCarOwnerList = carOwnerService.findAll();

        assertThat(carOwnerList).isEqualTo(expectedCarOwnerList);
    }

}
