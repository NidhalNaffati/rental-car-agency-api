package com.CarRentalAgency.config;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.repository.DealerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DealerConfiguration {


    @Bean
    CommandLineRunner commandLineRunner2(DealerRepository dealerRepository) {
        return args -> {
            faker(dealerRepository);
        };
    }

    private void faker(DealerRepository dealerRepository) {
        Faker facker = new Faker();

        for (int i = 0; i < 3; i++) {

            String email = facker.internet().safeEmailAddress();

            //avoiding having the same email address because it's unique for each user
            //(we can't have the same email for 2 users )
            if (dealerRepository.findByEmail(email).isPresent())
                continue;

            List<Car> carList = new ArrayList<>();

            Dealer dealer = new Dealer(1L, "myEmail@gmail.com", "myFirstName", "myLastName",carList);
            dealer.setFirstName(facker.name().firstName());
            dealer.setLastName(facker.name().lastName());
            dealer.setEmail(email);

            dealerRepository.save(dealer);
        }
    }
}

