package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;


    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }


    @Override
    public Car findCarById(long id) {
        return carRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO CAR ID LIKE THIS ONE: " + id)

                );
    }

    @Override
    public Car findCarByRegistrationNumber(int registrationNumber) {
        return carRepository.findCarByRegistrationNumber(registrationNumber)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO CAR REGISTRATION  NUMBER LIKE THIS ONE: " + registrationNumber)

                );
    }

    @Override
    public List<Car> findCarByCarName(String name) {
        return carRepository.findCarsByCarName(name);
    }


    @Override
    public List<Car> findCarsByKilometresLessThanEqual(int kilometre) {
        return carRepository.findCarsByKilometresLessThanEqual(kilometre);
    }

    @Override
    public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) {
        return carRepository.findCarsByKilometresGreaterThanEqual(kilometre);
    }

    @Override
    public List<Car> findCarsByModel(Car.Model model) {
        return carRepository.findCarsByModel(model);
    }


    @Override
    public List<Car> findCarsByFuel(Car.Fuel fuel) {
        return carRepository.findCarsByFuel(fuel);
    }


    @Override
    public List<Car> findCarsByGear(Car.Gear gear) {
        return carRepository.findCarsByGear(gear);
    }


}
