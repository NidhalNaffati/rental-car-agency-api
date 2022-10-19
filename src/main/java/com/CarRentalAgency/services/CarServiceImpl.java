package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public Car save(Car car){
       return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(long id) {
        return carRepository.findById(id);
    }


 /*   @Override
    public List<Car> findCarsWhereGearIsManual() {
        return null;
    }
*/
 /*   @Override
    public List<Car> findCarsWhereGearIsManual() {
        return carRepository.findCarsWhereGearIsManual();
    }
*/



}
