package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Dealer;

import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.DealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;

    @Override
    public List<Dealer> findAllDealer() {
        return dealerRepository.findAll();
    }


    // this method has the same functionality of save method save user
    @Override
    public Dealer saveDealer(Dealer dealer) throws AlreadyExistsException {
        Dealer existingDealer
                = dealerRepository.findByEmail(dealer.getEmail())
                .orElse(null);
        if (existingDealer == null) {
            dealerRepository.save(dealer);
            System.out.println("Car Owner added successfully");
        } else
            throw new AlreadyExistsException("Car Owner  already exists!!");

        return dealer;
    }

    @Override
    public Dealer findDealerById(Long id) throws NoSuchElementException {
        return dealerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("NO CAR_OWNER PRESENT WITH ID = " + id)
                );
    }

    @Override
    public Dealer findDealerByEmail(String email) throws NoSuchElementException {

        return dealerRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NoSuchElementException("there is no user with this email:" + email)
                );
    }

    @Override
    public void deleteDealerById(Long id) throws NoSuchElementException {
        Dealer existingDealer = dealerRepository.findById(id)
                .orElse(null);
        if (existingDealer == null)
            throw new NoSuchElementException("cannot delete unexisting user, this id: " + id
                    + " doesnt exist or he is already deleted.");
        else {
            dealerRepository.deleteById(id);
            System.out.println("Record deleted Successfully");
        }
    }


    @Override
    public Dealer updateDealer(Long id, Dealer dealer) throws NoSuchElementException {

        Dealer existingDealer = dealerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("we cannot update an user which he doesnt exist, make sure that" +
                                " ID = " + dealer.getId() + " is the correct ID.")
                );

        dealerRepository.findByEmail(dealer.getEmail())
                .orElseThrow(
                        () -> new AlreadyExistsException("this email is already exist, you should use another one.")
                );

        existingDealer.setEmail(dealer.getEmail());
        existingDealer.setFirstName(dealer.getFirstName());
        existingDealer.setLastName(dealer.getLastName());


        return existingDealer;
    }


}
