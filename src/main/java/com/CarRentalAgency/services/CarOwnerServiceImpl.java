package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.CarOwner;

import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CarOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

    @Autowired
    CarOwnerRepository carOwnerRepository;

    @Override
    public List<CarOwner> findAll() {
        return carOwnerRepository.findAll();
    }


    // this method has the same functionality of save method save user
    @Override
    public CarOwner addCarOwner(CarOwner carOwner) throws AlreadyExistsException {
        CarOwner existingCarOwner
                = carOwnerRepository.findByEmail(carOwner.getEmail())
                .orElse(null);
        if (existingCarOwner == null) {
            carOwnerRepository.save(carOwner);
            System.out.println("Car Owner added successfully");
        } else
            throw new AlreadyExistsException("Car Owner  already exists!!");

        return carOwner;
    }

    @Override
    public CarOwner findById(Long id) throws NoSuchElementException {
        return carOwnerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("NO CAR_OWNER PRESENT WITH ID = " + id)
                );
    }

    @Override
    public CarOwner findByEmail(String email) throws NoSuchElementException {

        return carOwnerRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NoSuchElementException("there is no user with this email:" + email)
                );
    }

    @Override
    public void deleteCarOwnerById(Long id) throws NoSuchElementException {
        CarOwner existingCarOwner = carOwnerRepository.findById(id)
                .orElse(null);
        if (existingCarOwner == null)
            throw new NoSuchElementException("cannot delete unexisting user, this id: " + id
                    + " doesnt exist or he is already deleted.");
        else {
            carOwnerRepository.deleteById(id);
            System.out.println("Record deleted Successfully");
        }
    }


    @Override
    public CarOwner update(Long id, CarOwner carOwner) throws NoSuchElementException {

        CarOwner existingCarOwner = carOwnerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("we cannot update an user which he doesnt exist, make sure that" +
                                " ID = " + carOwner.getId() + " is the correct ID.")
                );

        carOwnerRepository.findByEmail(carOwner.getEmail())
                .orElseThrow(
                        () -> new AlreadyExistsException("this email is already exist, you should use another one.")
                );

        existingCarOwner.setEmail(carOwner.getEmail());
        existingCarOwner.setFirstName(carOwner.getFirstName());
        existingCarOwner.setLastName(carOwner.getLastName());


        return existingCarOwner;
    }


}
