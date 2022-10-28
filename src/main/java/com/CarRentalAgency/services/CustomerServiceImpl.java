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
    public Customer findCustomerByEmail(String email) throws NoSuchElementException {

        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(
                        () -> new NoSuchElementException("THE IS NO CUSTOMER WITH THIS EMAIL :" + email +
                                " MAYBE YOU MEAN : " + customerRepository.approximateEmails(email))
                );
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
        Customer existingCustomer
                = customerRepository.findCustomerByEmail(customer.getEmail())
                .orElse(null);
        if (existingCustomer == null) {
            customerRepository.save(customer);
            System.out.println("DEALER ADDED SUCCESSFULLY ;)");
        } else
            throw new AlreadyExistsException("THIS EMAIL:" + customer.getEmail() + " ALREADY USED !!");
        return customer;
    }

    @Override
    public void deleteCustomerById(Long id) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElse(null);

        if (existingCustomer == null)
            throw new NoSuchElementException("CANNOT DELETE A NON-EXISTENT CUSTOMER WITH THIS ID: " + id
                    + " PROBABLY IT DOESNT EXIST OR HE IS ALREADY DELETED ;(");
        else {
            customerRepository.deleteById(id);
            System.out.println("RECORD DELETED  SUCCESSFULLY ;)");
        }

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

        return customerRepository.save(existingCustomer);
    }


}
