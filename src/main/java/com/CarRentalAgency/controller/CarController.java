package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/car")
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping(value = "/save")
    public Car saveCar(@Valid @RequestBody Car car) {
        return carService.save(car);
    }


    @GetMapping(value = "list/id/{id}")
    public Car fetchCarByID(@PathVariable Long id) {
        return carService.findCarById(id);
    }

    @GetMapping(value = "list/registrationNumber/{registrationNumber}")
    public Car fetchCarRegistrationNumber(@PathVariable int registrationNumber) {
        return carService.findCarByRegistrationNumber(registrationNumber);
    }

    @GetMapping(value = "list/name/{name}")
    public List<Car> fetchCarByName(@PathVariable String name) {
        return carService.findCarByCarName(name);
    }


    @GetMapping(value = "list/kilometre/less/{kilometre}")
    public List<Car> fetchCarsByKilometresLessThanEqual(@PathVariable int kilometre) {
        return carService.findCarsByKilometresLessThanEqual(kilometre);
    }

    @GetMapping(value = "list/kilometre/geater/{kilometre}")
    public List<Car> fetchCarsByKilometresGreaterThanEqual(@PathVariable int kilometre) {
        return carService.findCarsByKilometresGreaterThanEqual(kilometre);
    }

    @GetMapping(value = "list/fuel/{fuel}")
    public List<Car> fetchCarByManualFuel(@PathVariable Car.Fuel fuel) {
        return carService.findCarsByFuel(fuel);
    }

    @GetMapping(value = "list/gear/{gear}")
    public List<Car> fetchCarByManualGear(@PathVariable Car.Gear gear) {
        return carService.findCarsByGear(gear);
    }

    @GetMapping(value = "list/model/{model}")
    public List<Car> fetchCarByModel(@PathVariable Car.Model model) {
        return carService.findCarsByModel(model);
    }


}
