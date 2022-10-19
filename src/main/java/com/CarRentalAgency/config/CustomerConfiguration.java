package com.CarRentalAgency.config;

import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    CommandLineRunner commandLineRunner1(CustomerRepository customerRepository) {
        return args -> {
               faker(customerRepository);
        };
    }

    /* * this focking library is amazing hhhhh
     * i can fake user's full name and their mails
     * i dont need each time to insert some new data
     * i'm so glad after using this library
     * thanks amigoscode communnity .
     *  */


    // creating 4 fake users.
    private void faker(CustomerRepository customerRepository) {
        Faker facker = new Faker();

        for (int i = 0; i < 3; i++) {

            String email = facker.internet().safeEmailAddress();

            //avoiding having the same email address because it's unique for each customer
            //(we can't have the same email for 2 users )
            if (customerRepository.findCustomerByEmail(email).isPresent())
                continue;

            Customer customer = new Customer();
            customer.setFirstName(facker.name().firstName());
            customer.setLastName(facker.name().lastName());
            customer.setEmail(email);

            customerRepository.save(customer);
        }
    }
}
