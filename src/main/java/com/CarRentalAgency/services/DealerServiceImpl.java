package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements the {@link DealerService} interface and provides the necessary
 * implementations for interacting with the {@link Dealer} entity.
 */
@Service
public class DealerServiceImpl implements DealerService {

    /**
     * An instance of {@link DealerRepository} that provides methods for accessing the
     * customer repository.
     */
    private final DealerRepository dealerRepository;

    /**
     * Constructor for creating a new instance of {@link DealerServiceImpl} with the specified {@link DealerRepository}.
     *
     * @param dealerRepository the customer repository to be used for accessing the data.
     */
    public DealerServiceImpl(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    /**
     * Method to save a new dealer to the database.
     *
     * @param dealer the Dealer object to be saved.
     * @return the saved Dealer object.
     * @throws AlreadyExistsException if a dealer with the same email already exists in the database.
     */
    @Override
    public Dealer saveDealer(Dealer dealer) {
        // check if the dealer with the same email already exists
        Dealer existingDealer = dealerRepository
                .findByEmail(dealer.getEmail()) // return the dealer with the specified email
                .orElse(null); // if the dealer does not exist return null

        // if the email is not used by another dealer, save the dealer
        if (existingDealer == null) {
            dealerRepository.save(dealer);
        } else  // if the email is already used, throw an exception
            throw new AlreadyExistsException("THIS EMAIL:" + dealer.getEmail() + " ALREADY USED !!");
        return dealer;
    }

    /**
     * Updates the information of an existing dealer in the database.
     *
     * @param id        the unique identifier of the dealer to be updated.
     * @param newDealer an object of the Dealer class containing the updated dealer information.
     * @return the updated dealer object.
     * @throws NoSuchElementException if the dealer with the specified ID does not exist.
     * @throws AlreadyExistsException if a dealer with the same email already exists.
     */
    @Override
    public Dealer updateDealer(Long id, Dealer newDealer) {
        // check if the dealer with the specified ID exists
        Dealer existingDealer = dealerRepository
                .findById(id) // return the dealer with the specified ID
                .orElseThrow( // if the dealer does not exist throw an exception
                        () -> new NoSuchElementException("Dealer with id " + newDealer.getId() + " does not exist")
                );

        boolean isDealerEmailPresent = dealerRepository
                .findByEmail(newDealer.getEmail())
                .isPresent();

        // check if the email is already used by another customer
        if (isDealerEmailPresent)
            // if the email is already used, throw an exception
            throw new AlreadyExistsException("this email: " + newDealer.getEmail() + " already exists !!");
        else {
            // update the customer
            existingDealer.setFirstName(newDealer.getFirstName());
            existingDealer.setLastName(newDealer.getLastName());
            existingDealer.setEmail(newDealer.getEmail());
            existingDealer.setCarList(newDealer.getCarList());
        }
        return existingDealer;
    }

    /**
     * Deletes a dealer from the database.
     *
     * @param id the unique identifier of the dealer to be deleted.
     * @throws NoSuchElementException if the dealer with the specified ID does not exist.
     */
    @Override
    public void deleteDealerById(Long id) {

        // delete the customer with the specified id if it exists otherwise throw an exception
        dealerRepository.deleteById(
                dealerRepository
                        .findById(id) // if the customer exists return it
                        .orElseThrow( // if the customer does not exist throw an exception
                                () -> new NoSuchElementException("CANNOT DELETE A NON-EXISTENT DEALER WITH THIS ID: " + id
                                                                 + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(")
                        ).getId() // get the id of the customer
        );
    }

    /**
     * Returns a dealer with the specified id.
     *
     * @param id the unique identifier of the dealer to be returned.
     * @return the dealer with the specified id.
     * @throws NoSuchElementException if a dealer with the specified id does not exist.
     */
    @Override
    public Dealer findDealerById(Long id) {
        return dealerRepository
                .findById(id) // return the dealer with the specified ID
                .orElseThrow( // if the dealer does not exist throw an exception
                        () -> new NoSuchElementException("NO DEALER PRESENT WITH ID = " + id)
                );
    }

    /**
     * Returns a dealer with the specified email.
     *
     * @param dealerEmail the unique identifier of the dealer to be returned.
     * @return the dealer with the specified email.
     * @throws NoSuchElementException if a dealer with the specified email does not exist.
     */
    @Override
    public Dealer findDealerByEmail(String dealerEmail) {
        return dealerRepository
                .findByEmail(dealerEmail) // return the dealer with the specified email
                .orElseThrow( // if the dealer does not exist throw an exception
                        () -> new NoSuchElementException("OOPS THERE IS NO DEALER WITH THIS EMAIL = " + dealerEmail
                                                         + " MAYBE YOU MEAN : " + dealerRepository.approximateEmail(dealerEmail))
                );
    }

    /**
     * Returns a list of dealers with the specified first name.
     *
     * @param dealerName the first name of the dealer to be returned.
     * @return a list of dealers with the specified first name.
     * @throws NoSuchElementException if a dealer with the specified first name does not exist.
     */
    @Override
    public List<Dealer> findDealerByFirstNameIgnoreCase(String dealerName) {
        // printing the correct customer according the name.
        List<Dealer> dealerListFound = dealerRepository.findByFirstNameIgnoreCase(dealerName);

        if (dealerListFound.isEmpty()) {
            //throwing the exception & suggesting an approximate names from the db.
            throw new NoSuchElementException("OOPS THERE IS NO DEALER WITH THIS NAME ="
                                             + " MAYBE YOU MEAN : " + dealerRepository.approximateNames(dealerName));
        }
        return dealerListFound;
    }


    /**
     * Returns a list of all dealers in the database.
     *
     * @return a list of all dealers in the database.
     */
    @Override
    public List<Dealer> findAllDealers() {
        return dealerRepository.findAll();
    }

}
