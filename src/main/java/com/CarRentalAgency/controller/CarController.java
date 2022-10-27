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
@RequestMapping(value = "/api/v1/car")
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping(value = "/save")
    public Car saveCar(@Valid @RequestBody Car car) {
        return carService.saveCar(car);
    }


    @GetMapping(value = "list/id/{id}")
    public Car fetchCarByID(@PathVariable Long id) throws NoSuchElementException {
        return carService.findCarById(id);
    }

    @GetMapping(value = "list/registrationNumber/{registrationNumber}")
    public Car fetchCarRegistrationNumber(@PathVariable int registrationNumber) throws NoSuchElementException {
        return carService.findCarByRegistrationNumber(registrationNumber);
    }

    @GetMapping(value = "list/name/{name}")
    public List<Car> fetchCarByName(@PathVariable String name) throws NoSuchElementException {
        return carService.findCarsByCarName(name);
    }


    @GetMapping(value = "list/kilometre/less/{kilometre}")
    public List<Car> fetchCarsByKilometresLessThanEqual(@PathVariable int kilometre) throws NoSuchElementException {
        return carService.findCarsByKilometresLessThanEqual(kilometre);
    }

    @GetMapping(value = "list/kilometre/geater/{kilometre}")
    public List<Car> fetchCarsByKilometresGreaterThanEqual(@PathVariable int kilometre) throws NoSuchElementException {
        return carService.findCarsByKilometresGreaterThanEqual(kilometre);
    }


    @GetMapping(value = "list/model/{model}")
    public List<Car> fetchCarByModel(@PathVariable Car.Model model) throws NoSuchElementException {
        return carService.findCarsByModel(model);
    }


}
