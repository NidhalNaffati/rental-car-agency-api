package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.exception.NoSuchElementException;

import java.util.List;

public interface DealerService {

    List<Dealer> findAllDealers();

    List<Dealer> findDealerByFirstNameIgnoreCase(String userName) throws NoSuchElementException;

    Dealer saveDealer(Dealer dealer) throws AlreadyExistsException, java.util.NoSuchElementException;

    Dealer findDealerById(Long id) throws java.util.NoSuchElementException;

    Dealer findDealerByEmail(String email) throws java.util.NoSuchElementException;

    void deleteDealerById(Long id) throws java.util.NoSuchElementException;

    Dealer updateDealer(Long id, Dealer dealer) throws java.util.NoSuchElementException;
}
