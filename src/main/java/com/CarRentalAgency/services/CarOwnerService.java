package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.exception.AlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

public interface CarOwnerService {

    List<CarOwner> findAll();

    CarOwner addCarOwner(CarOwner carOwner) throws AlreadyExistsException, NoSuchElementException;

    CarOwner findById(Long id) throws NoSuchElementException;

    CarOwner findByEmail(String email) throws NoSuchElementException;

    void deleteCarOwnerById(Long id) throws NoSuchElementException;

    CarOwner update(Long id, CarOwner carOwner) throws NoSuchElementException;
}
