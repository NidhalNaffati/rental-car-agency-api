package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.services.CarOwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/carOwner")
public class CarOwnerController {

    @Autowired
    private CarOwnerServiceImpl carOwnerService;

    // done
    @GetMapping(value = "/list")
    public List<CarOwner> carOwnerList() {
        List<CarOwner> carOwnerList = carOwnerService.findAll();
        if (carOwnerList.isEmpty())
            throw new NoSuchElementException("THERE IS NO USER IN THE DATA BASE.");
        return carOwnerList;
    }

    @PostMapping(value = "/add")
    public CarOwner addCarOwner(@RequestBody CarOwner carOwner) throws AlreadyExistsException {
        return carOwnerService.addCarOwner(carOwner);
    }

    @GetMapping(value = "/get/{id}")
    public CarOwner findCarOwnerByID(@PathVariable Long id) {
        return carOwnerService.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String DeleteCarOwnerByID(@PathVariable Long id) throws NoSuchElementException {
        carOwnerService.deleteCarOwnerById(id);
        return "Deleted Successfully ;) ";
    }

    @PutMapping(value = "/update/{id}")
    public CarOwner updateCarOwner(@PathVariable Long id, @RequestBody @Valid CarOwner carOwner)
            throws NoSuchElementException, AlreadyExistsException {
        return carOwnerService.update(id, carOwner);
    }

}
