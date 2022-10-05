package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.exception.NotFoundException;

import java.util.List;

public interface CarOwnerService {

    List<CarOwner> findAll();

    CarOwner addCarOwner(CarOwner carOwner) throws AlreadyExistsException, NotFoundException;

    CarOwner findById(Long id) throws NotFoundException;

    CarOwner findByEmail(String email) throws NotFoundException;

    void deleteCarOwnerById(Long id) throws NotFoundException;

    CarOwner update(Long id, CarOwner carOwner) throws NotFoundException;
}
