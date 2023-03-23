package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements the {@link CustomerService} interface and provides the necessary
 * implementations for interacting with the {@link Customer} entity.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * An instance of {@link CustomerRepository} that provides methods for accessing the
     * customer repository.
     */
    private final CustomerRepository customerRepository;

    /**
     * Constructor for creating a new instance of {@link CustomerServiceImpl} with the specified {@link CustomerRepository}.
     *
     * @param customerRepository the customer repository to be used for accessing the data.
     */
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    /**
     * Saves the given customer object to the database.
     *
     * @param customer the customer object to be saved.
     * @return the saved customer object.
     * @throws AlreadyExistsException if a customer with the same email already exists.
     */
    @Override
    public Customer saveCustomer(Customer customer) {
        // check if the customer with the same email already exists
        Customer existingCustomer = customerRepository
                .findCustomerByEmail(customer.getEmail()) // find the customer with the same email
                .orElse(null); // if the customer does not exist return null

        // if the email is not used by another customer, save the customer
        if (existingCustomer == null)
            customerRepository.save(customer);
        else
            // if the email is already used, throw an exception
            throw new AlreadyExistsException("THIS EMAIL:" + customer.getEmail() + " ALREADY USED !!");
        return customer;
    }


    /**
     * Deletes a {@link Customer} with the given id from the database.
     *
     * @param id the unique identifier of the customer to be deleted.
     * @throws NoSuchElementException if a customer with the given id does not exist.
     */
    @Override
    public void deleteCustomerById(Long id) {
        // delete the customer with the specified id if it exists otherwise throw an exception
        customerRepository.deleteById(
                customerRepository
                        .findById(id) // if the customer exists return it
                        .orElseThrow( // if the customer does not exist throw an exception
                                () -> new NoSuchElementException("CANNOT DELETE A NON-EXISTENT CUSTOMER WITH THIS ID: " + id
                                                                 + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(")
                        ).getId() // get the id of the customer
        );
    }


    /**
     * Updates the information of an existing customer in the database.
     *
     * @param id          the unique identifier of the customer to be updated.
     * @param newCustomer an object of the Customer class containing the updated customer information.
     * @return the updated customer object.
     * @throws NoSuchElementException if the customer with the specified ID does not exist.
     * @throws AlreadyExistsException if a customer with the same email already exists.
     */
    @Override
    public Customer updateCustomer(Long id, Customer newCustomer) {

        // throw an exception if the customer with the specified ID does not exist
        if (!customerRepository.existsById(id))
            throw new NoSuchElementException("user id: " + id + " doesnt exist!!");

        // get the existing customer
        Customer existingCustomer = customerRepository.findCustomerById(id);

        boolean isCustomerEmailPresent = customerRepository
                .findCustomerByEmail(newCustomer.getEmail())
                .isPresent();

        // check if the email is already used by another customer
        if (isCustomerEmailPresent)
            // if the email is already used, throw an exception
            throw new AlreadyExistsException("this email: " + newCustomer.getEmail() + " already exists !!");
        else {
            // update the customer
            existingCustomer.setFirstName(newCustomer.getFirstName());
            existingCustomer.setLastName(newCustomer.getLastName());
            existingCustomer.setEmail(newCustomer.getEmail());
        }
        return customerRepository.save(existingCustomer);
    }


    /**
     * Returns a list of all the customers in the database.
     *
     * @return a list of all the customers in the database.
     */
    @Override
    public List<Customer> findAllCustomers() {
        // return all the customers
        return customerRepository.findAll();
    }


    /**
     * Returns a customer with the specified id.
     *
     * @param id the unique identifier of the customer to be returned.
     * @return the customer with the specified id.
     * @throws NoSuchElementException if a customer with the specified id does not exist.
     */
    @Override
    public Customer findCustomerById(Long id) throws NoSuchElementException {
        return customerRepository
                .findById(id) // find the customer with the specified id
                .orElseThrow( // if the customer does not exist throw an exception
                        () -> new NoSuchElementException("THERE IS NO CUSTOMER WITH THIS ID: " + id)
                );
    }


    /**
     * Returns a customer with the specified email.
     *
     * @param email the email of the customer to be returned.
     * @return the customer with the specified email.
     * @throws NoSuchElementException if a customer with the specified email does not exist.
     */
    @Override
    public Customer findCustomerByEmail(String email) throws NoSuchElementException {
        return customerRepository
                .findCustomerByEmail(email) // find the customer with the specified email
                .orElseThrow( // if the customer does not exist throw an exception
                        () -> new NoSuchElementException("THE IS NO CUSTOMER WITH THIS EMAIL :" + email +
                                                         " MAYBE YOU MEAN : " + customerRepository.approximateEmails(email))
                );
    }


    /**
     * Returns a list of customers with the specified first name.
     *
     * @param userName the first name of the customer to be returned.
     * @return a list of customers with the specified first name.
     * @throws NoSuchElementException if a customer with the specified first name does not exist.
     */
    @Override
    public List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws NoSuchElementException {
        // printing the correct customer according the name.
        List<Customer> customerListFound = customerRepository.findByFirstNameIgnoreCase(userName);
        if (customerListFound.isEmpty()) {
            //throwing the exception & suggesting an approximate names from the db.
            throw new NoSuchElementException("OOPS THIS CUSTOMER NAME DOESNT EXIST !! " +
                                             " MAYBE YOU MEAN : " + customerRepository.approximateNames(userName));
        }
        return customerListFound;
    }

}