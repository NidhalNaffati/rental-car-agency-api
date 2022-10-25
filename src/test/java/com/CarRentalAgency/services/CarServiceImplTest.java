package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.CarRentalAgency.entity.Car.Model.SPORTS_CAR;
import static com.CarRentalAgency.entity.Car.Model.SUV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {


    /**
     * @InjectMocks creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.
     */
    @InjectMocks
    private CarServiceImpl underTestService;


    /**
     * @Mock creates a mock implementation for the classes you need.
     */
    @Mock
    private CarRepository carRepository;

    Car car1;
    Car car2;

    List<Car> carList;

    @BeforeEach
    void setUp() {

        underTestService = new CarServiceImpl(carRepository);

        car1 = Car.builder()
                .id(1L)
                .registrationNumber(115)
                .name("BMW X5")
                .kilometres(20_000)
                .model(SUV)
                .gear(Car.Gear.Manual)
                .fuel(Car.Fuel.Diesel)
                .seats((short) 5)
                .doors((short) 44)
                .build();

        car2 = Car.builder()
                .id(2L)
                .registrationNumber(511)
                .name("Mercedes-Benz GLC Class GLC300")
                .kilometres(25_000)
                .model(SUV)
                .gear(Car.Gear.Automatic)
                .fuel(Car.Fuel.Petrol)
                .seats((short) 5)
                .doors((short) 4)
                .build();

    }

    @Test
    void save() {
        Car car = car1;
        //when
        underTestService.saveCar(car);
        //then
        verify(carRepository, times(1)).save(any());

    }

    @Test
    void findAllCars_ShouldReturnListOfCars() {
        // given
        carList = Arrays.asList(car1, car2);

        //when
        given(carRepository.findAll()).willReturn(carList);

        //then
        assertThat(underTestService.findAll())
                .hasSize(2)
                .contains(car1, car2);
    }

    @Test

    void findAllCars_ShouldThrowNoSuchElementException() {
      /*  // we will throw the exception because we have a emty list.

        //given
        carList = Arrays.asList();

        //when
        given(carRepository.findAll()).willReturn(carList);

        //then
        assertThrows(NoSuchElementException.class,
                () -> underTestService.findAll()
        );*/

        final String OUT_PUT_Message = "NO CAR ";

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(()->underTestService.findAll());



    }

    @Test
    void findCarById_ShouldReturnTheCar() {
        // given
        Car expected = car1;

        // when
        given(carRepository.findById(1L)).willReturn(Optional.ofNullable(expected));

        //then
        assertEquals(expected, underTestService.findCarById(1L));

    }

    @Test
    void findCarById_ShouldThrowNoSuchElementException() {

        Long nonExistingID = 999_999_999L;

        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarById(nonExistingID)
        );
    }

    @Test
    void findCarByRegistrationNumber_ShouldReturnTheCar() {
        // given
        car1.setRegistrationNumber(125);

        //when
        given(carRepository.findCarByRegistrationNumber(125)).willReturn(Optional.ofNullable(car1));

        //then
        assertThat(underTestService.findCarByRegistrationNumber(125))
                .isEqualTo(car1);
    }

    @Test
    void findCarByRegistrationNumber_ShouldThrowNoSuchElementException() {
        int nonExistingRegistrationNumber = 99999;

        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarByRegistrationNumber(nonExistingRegistrationNumber)
        );
    }

    @Test
    void findCarByCarName_ShouldReturnListOfCars() {
        //given
        final String CAR_NAME = "Mercedes-Benz GLC Class GLC300";
        car1.setName(CAR_NAME);
        carList = Arrays.asList(car1);

        //when
        given(carRepository.findCarsByCarName(CAR_NAME)).willReturn(carList);

        //then
        assertEquals(underTestService.findCarsByCarName(CAR_NAME), carList);

    }

    @Test
    void findCarByCarName_ShouldThrowNoSuchElementException() {

        //given
        final String CAR_NAME = "THIS CAR NAME DOESNT EXIST IN OUR UNIVERSE";

        //then
        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarsByCarName(CAR_NAME)
        );
    }

    @Test
    void findCarsByKilometresLessThanEqual_ShouldReturnListOfCars() {
        final int KILOMETRES = 500_000;

        car1.setKilometres(100_000);

        car2.setKilometres(200_000);

        carList = Arrays.asList(car1, car2);


        //when
        when(carRepository.findCarsByKilometresLessThanEqual(KILOMETRES)).thenReturn(carList);


        //when
        assertThat(underTestService.findCarsByKilometresLessThanEqual(KILOMETRES))
                .contains(car1, car2)
                .hasSize(2);
    }


    @Test
    void findCarsByKilometresLessThanEqual_ShouldThrowNoSuchElementException() {

        //given
        final int KILOMETRES = 50_000;

        car1.setKilometres(100_000);

        car2.setKilometres(200_000);

        //then
        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarsByKilometresLessThanEqual(KILOMETRES)
        );

    }

    @Test
    void findCarsByKilometresGreaterThanEqual_ShouldReturnListOfCars() {
        //given
        final int KILOMETRES = 5_000;

        car1.setKilometres(100_000);

        car2.setKilometres(200_000);

        carList = Arrays.asList(car1, car2);

        //when
        when(carRepository.findCarsByKilometresGreaterThanEqual(KILOMETRES)).thenReturn(carList);

        //then
        assertThat(underTestService.findCarsByKilometresGreaterThanEqual(KILOMETRES))
                .contains(car1, car2)
                .hasSize(2);
    }

    @Test
    void findCarsByKilometresGreaterThanEqual_ShouldThrowNoSuchElementException() {

        //given
        final int KILOMETRES = 500_000;

        car1.setKilometres(20_000);

        car2.setKilometres(80_000);

        //then
        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarsByKilometresGreaterThanEqual(KILOMETRES)
        );

    }


    @Test
    void findCarsByModel_ShouldReturnListOfCars() {
        //given
        final Car.Model MODEL = SPORTS_CAR;

        car1.setModel(MODEL);
        car2.setModel(MODEL);

        carList = Arrays.asList(car1, car2);

        //when
        when(carRepository.findCarsByModel(MODEL)).thenReturn(carList);

        //then
        assertThat(underTestService.findCarsByModel(MODEL))
                .hasSize(2)
                .contains(car1, car2);
    }

    @Test
    void findCarsByModel_ShouldThrowNoSuchElementException() {
        //given
        final Car.Model MODEL = SPORTS_CAR;

        car1.setModel(MODEL);
        car2.setModel(MODEL);

        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarsByModel(SUV)
        );
    }


}
