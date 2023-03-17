package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

        return carRepository.findCarsByCarName(name);
    }


    @Override
    public List<Car> findCarsByKilometresLessThanEqual(int kilometre) throws NoSuchElementException {

        return carRepository.findCarsByKilometresLessThanEqual(kilometre);
    }

    @Override
    public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) throws NoSuchElementException {

        return carRepository.findCarsByKilometresGreaterThanEqual(kilometre);
    }

    @Override
    public List<Car> findCarsByModel(Car.Model model) throws NoSuchElementException {

        return carRepository.findCarsByModel(model);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository
                .deleteCarById(
                        carRepository
                                .findById(id) // find the car exists
                                .orElseThrow(
                                        () -> new NoSuchElementException("THERE IS NO CAR WITH THIS ID: " + id) // if not throw exception
                                ).getId() // get the id of the car
                );
    }

}