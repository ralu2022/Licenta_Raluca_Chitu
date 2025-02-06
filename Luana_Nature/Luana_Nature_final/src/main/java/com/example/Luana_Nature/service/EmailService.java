package com.example.Luana_Nature.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender notification;


    public void sendEmail(String userEmail, String subject, String text,String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(userName);
        message.setFrom("luananature2024@gmail.com");

        try {
            notification.send(message);
            System.out.println("Email trimis cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eroare la trimiterea email-ului " + e.getMessage());}
    }

    public void sendAdminEmail(String toAdmin, String subject, String text) {
        SimpleMailMessage adminMessage = new SimpleMailMessage();
        adminMessage.setTo(toAdmin);
        adminMessage.setSubject(subject);
        adminMessage.setText(text);
        notification.send(adminMessage);

        try {
            notification.send(adminMessage);
            System.out.println("Email trimis cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eroare la trimiterea email-ului: " + e.getMessage());}
    }
}
