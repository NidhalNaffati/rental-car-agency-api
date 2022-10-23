package com.CarRentalAgency.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {


    private final JavaMailSender mailSender;

    public void sendEmail(String reciverEmail,
                          String emailsubject,
                          String emailbody) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("nidhalnaffati@gmail.com");
        mailMessage.setTo(reciverEmail);
        mailMessage.setText(emailbody);
        mailMessage.setSubject(emailsubject);

        mailSender.send(mailMessage);
        System.out.println("Mail Sent Successfully");
    }
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
