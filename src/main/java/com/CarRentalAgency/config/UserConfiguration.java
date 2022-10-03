package com.CarRentalAgency.config;

import com.CarRentalAgency.entity.User;
import com.CarRentalAgency.exception.EmailAlreadyExists;
import com.CarRentalAgency.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            faker(userRepository);
        } ;
    }

    /*
    * this focking library is amazing hhhhh
    * i can fake user's full name and their mails
    * i dont need each time to insert some new data
    * i'm so glad after using this library
    * thanks amigoscode communnity .
    *  */

    // creating 4 fake users.
    private void faker(UserRepository userRepository)  throws EmailAlreadyExists {
        Faker facker = new Faker();

        for(int i=0;i<3;i++){

            String email = facker.internet().safeEmailAddress();

            //avoiding having the same email address because it's unique for each user
            //(we can't have the same email for 2 users )
            if(userRepository.findUserByEmail(email).isPresent())
                continue;

            User user = new User();
            user.setFirstName(facker.name().firstName());
            user.setLastName(facker.name().lastName());
            user.setEmail(email);

            userRepository.save(user);
        }
    }
}
