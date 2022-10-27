package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    //private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public Customer findCustomerById(Long id) throws NoSuchElementException {


        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("THERE IS NO CUSTOMER WITH THIS ID: " + id)
                );

    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) throws NoSuchElementException {
        Optional<Customer> userOptional = customerRepository.findCustomerByEmail(email);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("THE IS NO CUSTOMER WITH THIS EMAIL :" + email +
                    " MAYBE YOU MEAN : " + customerRepository.approximateEmails(email));
        return userOptional;
    }


    @Override
    public List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws NoSuchElementException {
        // printing the correct customer according the name.
        List<Customer> customerListFound = customerRepository.findByFirstNameIgnoreCase(userName);
        if (customerListFound.isEmpty()) {
            //throwing the exception & suggesting a approximate names from the db.
            throw new NoSuchElementException("OOPS THIS CUSTOMER NAME DOESNT EXIST !! " +
                    " MAYBE YOU MEAN : " + customerRepository.approximateNames(userName));
        }
        return customerListFound;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> userDB = customerRepository.findCustomerByEmail(customer.getEmail());
        if (userDB.isPresent())
            throw new AlreadyExistsException("THIS EMAIL : " + customer.getEmail() + " ALREADY EXISTS.");
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> userOptional = customerRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("CANNOT DELETE NON-EXISTING CUSTOMER, THIS ID: " + id
                    + " DOESNT EXIST OR HE IS ALREADY DELETED.");
        customerRepository.deleteById(id);

    }


    @Override
    public Customer updateCustomer(Long id, Customer newCustomer) throws NoSuchElementException, AlreadyExistsException {

        Optional<Customer> optionalUser = customerRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new NoSuchElementException("user id: " + id + " doesnt exist!!");

        Customer existingCustomer = optionalUser.get();

        if (customerRepository.findCustomerByEmail(newCustomer.getEmail()).isPresent())
            throw new AlreadyExistsException("this email: " + newCustomer.getEmail() + " already exists !!");

        existingCustomer.setFirstName(newCustomer.getFirstName());
        existingCustomer.setLastName(newCustomer.getLastName());
        existingCustomer.setEmail(newCustomer.getEmail());

       /* if (Objects.nonNull(userDB.getFirstName()) &&
                !"".equalsIgnoreCase(userDB.getFirstName())) {
            userDB.setFirstName(newCustomer.getFirstName());
        }
       */
        return customerRepository.save(existingCustomer);
    }


}
