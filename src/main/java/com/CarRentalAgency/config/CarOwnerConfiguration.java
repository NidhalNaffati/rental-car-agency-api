package com.CarRentalAgency.config;

import com.CarRentalAgency.entity.CarOwner;
import com.CarRentalAgency.repository.CarOwnerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarOwnerConfiguration {


    @Bean
    CommandLineRunner commandLineRunner2(CarOwnerRepository carOwnerRepository) {
        return args -> {
            faker(carOwnerRepository);
        };
    }

    private void faker(CarOwnerRepository carOwnerRepository) {
        Faker facker = new Faker();

        for (int i = 0; i < 3; i++) {

            String email = facker.internet().safeEmailAddress();

            //avoiding having the same email address because it's unique for each user
            //(we can't have the same email for 2 users )
            if (carOwnerRepository.findByEmail(email).isPresent())
                continue;

            CarOwner carOwner = new CarOwner();
            carOwner.setFirstName(facker.name().firstName());
            carOwner.setLastName(facker.name().lastName());
            carOwner.setEmail(email);

            carOwnerRepository.save(carOwner);
        }
    }
}

