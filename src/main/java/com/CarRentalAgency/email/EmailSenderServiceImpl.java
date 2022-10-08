package com.CarRentalAgency.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

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
