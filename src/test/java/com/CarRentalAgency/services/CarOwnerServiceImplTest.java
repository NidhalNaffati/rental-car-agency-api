package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.repository.CarOwnerRepository;
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

    @Test
    void findAll_GivenAListAsParam_ExpectingTheSameList() {
        List<Car> carList = new ArrayList<>();

        CarOwner carOwner1 = new CarOwner(
                1L,
                "myEmail1@gmail.com",
                "myFirstName1",
                "myLastName1",
                carList);

        CarOwner carOwner2 = new CarOwner(
                2L,
                "myEmail2@gmail.com",
                "myFirstName2",
                "myLastName2",
                carList);

        List<CarOwner> carOwnerList = Arrays.asList(carOwner1, carOwner2);


        given(carOwnerRepository.findAll())
                .willReturn(carOwnerList);

        assertThat(carOwnerService.findAll())
                .isEqualTo(Arrays.asList(carOwner1,carOwner2));

        assertThat(carOwnerService.findAll())
                .hasSize(2)
                .contains(carOwner1, carOwner2);
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
