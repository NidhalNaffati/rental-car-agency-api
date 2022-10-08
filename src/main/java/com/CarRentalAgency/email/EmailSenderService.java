package com.CarRentalAgency.email;

public interface EmailSenderService {
    void sendEmail(String reciverEmail, String emailsubject, String emailbody);
}
