package com.CarRentalAgency.config;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.repository.DealerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
public class DealerConfiguration {
    @Bean
    CommandLineRunner commandLineRunner2(DealerRepository dealerRepository) {
        return args -> insertDealersToTheDB(dealerRepository);
    }

    private void insertDealersToTheDB(DealerRepository dealerRepository) {
        Faker facker = new Faker();

        for (int i = 0; i < 3; i++) {

            String email = facker.internet().safeEmailAddress();

            //avoiding having the same email address because it's unique for each user
            //(we can't have the same email for 2 users )
            if (dealerRepository.findByEmail(email).isPresent())
                continue;

            Dealer dealer = new Dealer();
            dealer.setFirstName(facker.name().firstName());
            dealer.setLastName(facker.name().lastName());
            dealer.setEmail(email);

            dealerRepository.save(dealer);
        }
    }
}

*/
