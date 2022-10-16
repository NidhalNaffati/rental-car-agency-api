package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    //private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> userDB = customerRepository.findCustomerByEmail(customer.getEmail());
        if (userDB.isPresent())
            throw new AlreadyExistsException("this email: " + customer.getEmail() + " already exists.");
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public List<Customer> findCustomerByFirstNameIgnoreCase(String userName) throws NoSuchElementException {
        List<Customer> customerListFound = customerRepository.findByFirstNameIgnoreCase(userName);
        if (customerListFound.isEmpty()) {
            //   LOGGER.error("ERROR printing the user name.");
            throw new NoSuchElementException("Oops This userName doesnt exist !! " +
                    "Maybe you mean: " /*+ userRepository.WrongNames(userName).get(1).getFirstName()*/);
        }
        return customerListFound;
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) throws NoSuchElementException {
        Optional<Customer> userOptional = customerRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("there is no user with this id:" + id);
        return userOptional;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) throws NoSuchElementException {
        Optional<Customer> userOptional = customerRepository.findCustomerByEmail(email);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("there is no user with this email:" + email);
        return userOptional;
    }


    @Override
    public void deleteCustomerById(Long id)  {
        Optional<Customer> userOptional = customerRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NoSuchElementException("cannot delete unexisting user, this id: " + id
                    + " doesnt exist or he is already deleted.");
        customerRepository.deleteById(id);

    }


    // TODO: 04/10/2022 i  should handel the null fields when i save or update.
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
