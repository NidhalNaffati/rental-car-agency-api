package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;


    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {

        List<Car> carList = carRepository.findAll();
        if (carList.isEmpty())
            throw new NoSuchElementException("THERE IS NO CAR");

        return carList;


    }


    @Override
    public Car findCarById(long id) throws NoSuchElementException {
        return carRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO CAR ID LIKE THIS ONE: " + id)
                );
    }

    @Override
    public Car findCarByRegistrationNumber(int registrationNumber) throws NoSuchElementException {
        return carRepository.findCarByRegistrationNumber(registrationNumber)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO CAR REGISTRATION  NUMBER LIKE THIS ONE: " + registrationNumber)
                );
    }

    @Override
    public List<Car> findCarsByCarName(String name) throws NoSuchElementException {
        List<Car> carList = carRepository.findCarsByCarName(name);
        if (carList.isEmpty())
            throw new NoSuchElementException("THERE IS NO CAR WITH A NAME LIKE THIS: " + name);

        return carList;
    }


    @Override
    public List<Car> findCarsByKilometresLessThanEqual(int kilometre) throws NoSuchElementException {
        List<Car> carList = carRepository.findCarsByKilometresLessThanEqual(kilometre);

        if (carList.isEmpty())
            throw new NoSuchElementException("THERE IS NO CAR WHICH HAS KILOMETRES LESS THAN OR EQUAL TO: " + kilometre);

        return carList;
    }

    @Override
    public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) throws NoSuchElementException {
        List<Car> carList = carRepository.findCarsByKilometresGreaterThanEqual(kilometre);

        if (carList.isEmpty())
            throw new NoSuchElementException("THERE IS NO CAR WHICH HAS KILOMETRES GREATER THAN OR EQUAL TO: " + kilometre);

        return carList;
    }

    @Override
    public List<Car> findCarsByModel(Car.Model model) throws NoSuchElementException {
        List<Car> carList = carRepository.findCarsByModel(model);
        if (carList.isEmpty())
            throw new NoSuchElementException("THERE IS NO CAR MODEL LIKE: " + model);

        return carList;
    }


}
