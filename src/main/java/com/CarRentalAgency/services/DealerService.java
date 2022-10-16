package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.AlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

public interface DealerService {

    List<Dealer> findAllDealer();

    Dealer saveDealer(Dealer dealer) throws AlreadyExistsException, NoSuchElementException;

    Dealer findDealerById(Long id) throws NoSuchElementException;

    Dealer findDealerByEmail(String email) throws NoSuchElementException;

    void deleteDealerById(Long id) throws NoSuchElementException;

    Dealer updateDealer(Long id, Dealer dealer) throws NoSuchElementException;
}
