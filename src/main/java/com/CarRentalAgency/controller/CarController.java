package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/cars")
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping
    public Car saveCar(@Valid @RequestBody Car car) {
        return carService.saveCar(car);
    }


    @GetMapping(value = "{id}")
    public Car fetchCarByID(@PathVariable Long id) throws NoSuchElementException {
        return carService.findCarById(id);
    }

    @GetMapping(value = "/registration-number/{registrationNumber}")
    public Car fetchCarRegistrationNumber(@PathVariable int registrationNumber) throws NoSuchElementException {
        return carService.findCarByRegistrationNumber(registrationNumber);
    }

    @GetMapping(value = "{name}")
    public List<Car> fetchCarByName(@PathVariable String name) throws NoSuchElementException {
        return carService.findCarsByCarName(name);
    }


    @GetMapping(value = "less/{kilometre}")
    public List<Car> fetchCarsByKilometresLessThanEqual(@PathVariable int kilometre) throws NoSuchElementException {
        return carService.findCarsByKilometresLessThanEqual(kilometre);
    }

    @GetMapping(value = "greater/{kilometre}")
    public List<Car> fetchCarsByKilometresGreaterThanEqual(@PathVariable int kilometre) throws NoSuchElementException {
        return carService.findCarsByKilometresGreaterThanEqual(kilometre);
    }


    @GetMapping(value = "list/model/{model}")
    public List<Car> fetchCarByModel(@PathVariable Car.Model model) throws NoSuchElementException {
        return carService.findCarsByModel(model);
    }


}
