package com.CarRentalAgency;

import com.CarRentalAgency.email.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CarRentalAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalAgencyApplication.class, args);
    }

   /*
   sending mails
    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        emailSenderService.sendEmail(
                "nidhalnaffati22@gmail.com",
                "Spring boot ",
                "Assalem aleikom \n all great & hmd."
        );
    }*/
}
