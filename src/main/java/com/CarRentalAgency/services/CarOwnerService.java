package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.CarOwner;

import java.util.List;

public interface CarOwnerService {

    List<CarOwner> findAll();

    CarOwner save(CarOwner carOwner);

    CarOwner update(CarOwner carOwner);

    void delete(Long id);
}
