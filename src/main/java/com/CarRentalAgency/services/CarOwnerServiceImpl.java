package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.repository.CarOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOwnerServiceImpl implements CarOwnerService{

    @Autowired
    CarOwnerRepository ownerRepository;

    @Override
    public List<CarOwner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public CarOwner save(CarOwner carOwner) {
        return null;
    }

    @Override
    public CarOwner update(CarOwner carOwner) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
