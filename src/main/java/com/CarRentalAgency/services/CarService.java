package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car saveCar(Car car);

    Car findCarById(long id);

    Car findCarByRegistrationNumber(int registrationNumber);

    List<Car> findCarsByCarName(String name);

    List<Car> findCarsByModel(Car.Model model);

    List<Car> findCarsByKilometresLessThanEqual(int kilometre);

    List<Car> findCarsByKilometresGreaterThanEqual(int kilometre);

    void deleteCarById(Long id);

    Car updateCar(Long id, Car newCar);
}
