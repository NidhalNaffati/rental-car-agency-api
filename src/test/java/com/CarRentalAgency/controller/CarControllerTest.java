package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarControllerTest {

  @Mock
  private CarService carService;

  @InjectMocks
  private CarController carController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveCar_shouldReturnCreatedStatus() {
    Car car = new Car();
    when(carService.saveCar(car)).thenReturn(car);

    ResponseEntity<Car> response = carController.saveCar(car);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(car, response.getBody());
  }

  @Test
  void updateCar_shouldReturnUpdatedCar() {
    Long id = 1L;
    Car newCar = new Car();
    Car updatedCar = new Car();
    when(carService.updateCar(id, newCar)).thenReturn(updatedCar);

    ResponseEntity<Car> response = carController.updateCar(id, newCar);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedCar, response.getBody());
  }

  @Test
  void deleteCarById_shouldReturnNoContentStatus() {
    Long id = 1L;

    ResponseEntity<Void> response = carController.deleteCarById(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(carService, times(1)).deleteCarById(id);
  }

  @Test
  void findCarById_shouldReturnCar() {
    Long id = 1L;
    Car car = new Car();
    when(carService.findCarById(id)).thenReturn(car);

    ResponseEntity<Car> response = carController.findCarById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(car, response.getBody());
  }

  @Test
  void findAllCars_shouldReturnListOfCars() {
    List<Car> cars = new ArrayList<>();
    when(carService.findAll()).thenReturn(cars);

    List<Car> response = carController.findAllCars();

    assertEquals(cars, response);
  }

  @Test
  void findCarByRegistrationNumber_shouldReturnCar() {
    int registrationNumber = 12345;
    Car car = new Car();
    when(carService.findCarByRegistrationNumber(registrationNumber)).thenReturn(car);

    ResponseEntity<Car> response = carController.findCarByRegistrationNumber(registrationNumber);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(car, response.getBody());
  }

  @Test
  void findCarsByCarName_shouldReturnListOfCars() {
    String carName = "Sedan";
    List<Car> cars = new ArrayList<>();
    when(carService.findCarsByCarName(carName)).thenReturn(cars);

    ResponseEntity<List<Car>> response = carController.findCarsByCarName(carName);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cars, response.getBody());
  }

  @Test
  void findCarsByModel_shouldReturnListOfCars() {
    Car.Model model = Car.Model.SUV;
    List<Car> cars = new ArrayList<>();
    when(carService.findCarsByModel(model)).thenReturn(cars);

    ResponseEntity<List<Car>> response = carController.findCarsByModel(model);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cars, response.getBody());
  }

  @Test
  void findCarsByKilometresLessThanEqual_shouldReturnListOfCars() {
    int kilometre = 10000;
    List<Car> cars = new ArrayList<>();
    when(carService.findCarsByKilometresLessThanEqual(kilometre)).thenReturn(cars);

    ResponseEntity<List<Car>> response = carController.findCarsByKilometresLessThanEqual(kilometre);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cars, response.getBody());
  }

  @Test
  void findCarsByKilometresGreaterThanEqual_shouldReturnListOfCars() {
    int kilometre = 10000;
    List<Car> cars = new ArrayList<>();
    when(carService.findCarsByKilometresGreaterThanEqual(kilometre)).thenReturn(cars);

    ResponseEntity<List<Car>> response = carController.findCarsByKilometresGreaterThanEqual(kilometre);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cars, response.getBody());
  }
}