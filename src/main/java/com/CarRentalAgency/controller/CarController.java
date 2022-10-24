package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/car")
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping(value = "/save")
    public Car saveCar(@Valid @RequestBody Car car)  {
        return carService.save(car);
    }


}
