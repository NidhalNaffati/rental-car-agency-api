package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    Car findCarById(long id);

    Car findCarByRegistrationNumber(int registrationNumber);

    List<Car> findCarByCarName(String name);


    List<Car> findCarsByGear(Car.Gear gear);

    List<Car> findCarsByModel(Car.Model model);


    List<Car> findCarsByFuel(Car.Fuel fuel);


    List<Car> findCarsByKilometresLessThanEqual(int kilometre);


    List<Car> findCarsByKilometresGreaterThanEqual(int kilometre);
}
