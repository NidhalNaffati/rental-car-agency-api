package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@Valid @RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car newCar) {
        Car updatedCar = carService.updateCar(id, newCar);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Long id) {
        Car car = carService.findCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public List<Car> findAllCars() {
        return carService.findAll();
    }

    @GetMapping(params = "registrationNumber")
    public ResponseEntity<Car> findCarByRegistrationNumber(@RequestParam int registrationNumber) {
        Car car = carService.findCarByRegistrationNumber(registrationNumber);
        return ResponseEntity.ok(car);
    }

    @GetMapping(params = "carName")
    public ResponseEntity<List<Car>> findCarsByCarName(@RequestParam String carName) {
        List<Car> carsByCarName = carService.findCarsByCarName(carName);
        return ResponseEntity.ok(carsByCarName);
    }

    @GetMapping(params = "model")
    public ResponseEntity<List<Car>> findCarsByModel(@RequestParam Car.Model model) {
        List<Car> carsByModel = carService.findCarsByModel(model);
        return ResponseEntity.ok(carsByModel);
    }

    @GetMapping(params = {"kilometre", "lesser"})
    public ResponseEntity<List<Car>> findCarsByKilometresLessThanEqual(@RequestParam int kilometre) {
        List<Car> carsByKilometresLessThanEqual = carService.findCarsByKilometresLessThanEqual(kilometre);
        return ResponseEntity.ok(carsByKilometresLessThanEqual);
    }

    @GetMapping(params = {"kilometre", "greater"})
    public ResponseEntity<List<Car>> findCarsByKilometresGreaterThanEqual(@RequestParam int kilometre) {
        List<Car> carsByKilometresGreaterThanEqual = carService.findCarsByKilometresGreaterThanEqual(kilometre);
        return ResponseEntity.ok(carsByKilometresGreaterThanEqual);

    }
}