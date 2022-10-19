package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    Optional<Car> findById(long id);

/*
    List<Car> findCarsWhereGearIsAutomatic();

    List<Car> findCarsWhereGearIsManual();
*/

}
