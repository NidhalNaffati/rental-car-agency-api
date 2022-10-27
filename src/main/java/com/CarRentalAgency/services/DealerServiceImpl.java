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
    public List<Dealer> findAllDealers() {
        return dealerRepository.findAll();
    }


    @Override
    public Dealer findDealerById(Long id) throws NoSuchElementException {
        return dealerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("NO DEALER PRESENT WITH ID = " + id)
                );
    }

    @Override
    public Dealer findDealerByEmail(String dealerEmail) throws NoSuchElementException {

        return dealerRepository.findByEmail(dealerEmail)
                .orElseThrow(
                        () -> new NoSuchElementException("OOPS THERE IS NO DEALER WITH THIS EMAIL = " + dealerEmail
                        +" MAYBE YOU MEAN : "+dealerRepository.approximateEmail(dealerEmail))
                );
    }

    @Override
    public List<Dealer> findDealerByFirstNameIgnoreCase(String dealerName) throws NoSuchElementException {
        List<Dealer> customerListFound = dealerRepository.findByFirstNameIgnoreCase(dealerName);
        if (customerListFound.isEmpty()) {
            //   LOGGER.error("ERROR printing the user name.");
            throw new NoSuchElementException("OOPS THERE IS NO DEALER WITH THIS NAME ="
                    + " MAYBE YOU MEAN : " + dealerRepository.approximateNames(dealerName));
        }
        return customerListFound;
    }

    @Override
    public Dealer saveDealer(Dealer dealer) throws AlreadyExistsException {
        Dealer existingDealer
                = dealerRepository.findByEmail(dealer.getEmail())
                .orElse(null);
        if (existingDealer == null) {
            dealerRepository.save(dealer);
            System.out.println("DEALER ADDED SUCCESSFULLY ;)");
        } else
            throw new AlreadyExistsException("DEALER  ALREADY EXISTS !!");

        return dealer;
    }

    @Override
    public void deleteDealerById(Long id) throws NoSuchElementException {
        Dealer existingDealer = dealerRepository.findById(id)
                .orElse(null);
        if (existingDealer == null)
            throw new NoSuchElementException("CANNOT DELETE A NON-EXISTENT DEALER WITH THIS ID: " + id
                    + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(");
        else {
            dealerRepository.deleteById(id);
            System.out.println("RECORD DELETED  SUCCESSFULLY ;)");
        }
    }


    @Override
    public Dealer updateDealer(Long id, Dealer dealer) throws NoSuchElementException {

        Dealer existingDealer = dealerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("WE CANNOT UPDATE A NON-EXISTENT DEALER, make sure that" +
                                " ID = " + dealer.getId() + " IS THE CORRECT ID.")
                );

        dealerRepository.findByEmail(dealer.getEmail())
                .orElseThrow(
                        () -> new AlreadyExistsException("THIS EMAIL IS ALREADY USED, YOU SHOULD USE ANOTHER ONE.")
                );

        existingDealer.setEmail(dealer.getEmail());
        existingDealer.setFirstName(dealer.getFirstName());
        existingDealer.setLastName(dealer.getLastName());


        return existingDealer;
    }


}
