package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.CarRentalAgency.entity.Car.Model.SPORTS_CAR;
import static com.CarRentalAgency.entity.Car.Model.SUV;
import static org.assertj.core.api.Assertions.assertThat;
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

    // 2 cars for testing.
    Car car1;
    Car car2;

    // list of cars for testing.
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
    void testSaveCar_ShouldReturnTheSavedCar() {
        // given
        Car car = car1;

        //when
        underTestService.saveCar(car);
        //then
        verify(carRepository, times(1)).save(any());

    }

    @Test
    void testSaveCar_ShouldThrowAlreadyExistsException() {
        // given
        Car newCar = car1;
        Car existingCar = car2;

        int registrationNumber = newCar.getRegistrationNumber();

        // when
        when(carRepository.findCarByRegistrationNumber(registrationNumber)).thenReturn(Optional.of(existingCar));

        // then
        assertThrows(AlreadyExistsException.class, () -> underTestService.saveCar(newCar));
        verify(carRepository, times(0)).save(newCar);
    }

    @Test
    void testDeleteCarByID_ShouldDeleteTheCar() {
        // given;
        Long id = car1.getId();

        // when
        when(carRepository.findById(id)).thenReturn(java.util.Optional.of(car1));
        doNothing().when(carRepository).deleteCarById(id);

        // then
        // Verify that the car with the specified ID is deleted
        assertDoesNotThrow(() -> underTestService.deleteCarById(id));
        verify(carRepository, times(1)).deleteCarById(id);
    }

    @Test
    void testDeleteCarById_ShouldThrowNoSuchElementException() {
        // given
        Long id = 1L;

        // when
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        // then
        // Verify that the NoSuchElementException is thrown when the car with the specified ID does not exist
        assertThrows(NoSuchElementException.class, () -> underTestService.deleteCarById(id));
        verify(carRepository, times(0)).deleteCarById(id);
    }

    @Test
    public void testUpdateCarWithANonUsedRegistrationNumber_ShouldReturnTheNewCar() {
        // given
        Car existingCar = car1;
        long carId = existingCar.getId();

        Car newCar = car2;

        // when
        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));
        when(carRepository.findCarByRegistrationNumber(newCar.getRegistrationNumber())).thenReturn(Optional.empty());
        when(carRepository.save(existingCar)).thenReturn(existingCar);

        Car updatedCar = underTestService.updateCar(carId, newCar);


        // then
        verify(carRepository, times(1)).save(existingCar);
        assertEquals(newCar.getRegistrationNumber(), updatedCar.getRegistrationNumber());
        assertEquals(newCar.getName(), updatedCar.getName());
        assertEquals(newCar.getModel(), updatedCar.getModel());
        assertEquals(newCar.getRegistrationNumber(), updatedCar.getRegistrationNumber());
        assertEquals(newCar.getSeats(), updatedCar.getSeats());
        assertEquals(newCar.getGear(), updatedCar.getGear());
        assertEquals(newCar.getDoors(), updatedCar.getDoors());
        assertEquals(newCar.getKilometres(), updatedCar.getKilometres());
        assertEquals(newCar.getFuel(), updatedCar.getFuel());
    }

    @Test
    public void testUpdateCarWithAlreadyUsedRegistrationNumber() {
        // Given
        Car existingCar = car1;

        long id = existingCar.getId();

        Car newCar = car2;
        int newCarRegistrationNumber = newCar.getRegistrationNumber();

        when(carRepository.findById(id))
                .thenReturn(Optional.of(existingCar));

        when(carRepository.findCarByRegistrationNumber(newCarRegistrationNumber))
                .thenReturn(Optional.of(newCar));

        // When
        AlreadyExistsException exception = assertThrows(
                AlreadyExistsException.class,
                () -> underTestService.updateCar(id, newCar)
        );

        // Then
        assertEquals(
                String.format("The registration number %d was used by another car", newCar.getRegistrationNumber()),
                exception.getMessage()
        );

        verify(carRepository, never()).save(existingCar);
    }

    @Test
    public void testUpdateCar_ShouldThrowNoSuchElementException() {
        // given
        Car newCar = car1;
        Long id = 1L;

        // when
        when(carRepository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(NoSuchElementException.class, () -> underTestService.updateCar(id, newCar));

        verify(carRepository, times(0)).findCarByRegistrationNumber(anyInt());
        verify(carRepository, times(0)).save(any(Car.class));
    }

    @Test
    void testFindAllCars_ShouldReturnListOfCars() {
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
    void testFindCarById_ShouldReturnTheCar() {
        // given
        Car expectedCar = car1;

        // when
        when(carRepository.findById(1L)).thenReturn(Optional.of(expectedCar));

        //then
        assertEquals(expectedCar, underTestService.findCarById(1L));
    }

    @Test
    void testFindCarById_ShouldThrowNoSuchElementException() {
        // given
        long nonExistingID = 999_999_999L;

        // then
        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarById(nonExistingID)
        );
    }

    @Test
    void testFindCarByRegistrationNumber_ShouldReturnTheCar() {
        // given
        int registrationNumber = car1.getRegistrationNumber();

        //when
        given(carRepository.findCarByRegistrationNumber(registrationNumber)).willReturn(Optional.ofNullable(car1));

        //then
        assertThat(underTestService.findCarByRegistrationNumber(registrationNumber))
                .isEqualTo(car1);
    }

    @Test
    void testFindCarByRegistrationNumber_ShouldThrowNoSuchElementException() {
        int nonExistingRegistrationNumber = 999_999_999;

        assertThrows(NoSuchElementException.class,
                () -> underTestService.findCarByRegistrationNumber(nonExistingRegistrationNumber)
        );
    }

    @Test
    void testFindCarByCarName_ShouldReturnListOfCars() {
        //given
        final String CAR_NAME = "Mercedes-Benz GLC Class GLC300";
        car1.setName(CAR_NAME);
        carList = List.of(car1);

        //when
        given(carRepository.findCarsByCarName(CAR_NAME)).willReturn(carList);

        //then
        assertEquals(underTestService.findCarsByCarName(CAR_NAME), carList);
    }

    @Test
    void testFindCarsByKilometresLessThanEqual_ShouldReturnListOfCars() {
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
    void testFindCarsByKilometresGreaterThanEqual_ShouldReturnListOfCars() {
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
    void testFindCarsByModel_ShouldReturnListOfCars() {
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
}