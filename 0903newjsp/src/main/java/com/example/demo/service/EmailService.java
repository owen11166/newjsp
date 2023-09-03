package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationConfirmation(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("5team44684468@gmail.com");
        message.setTo(toEmail);
        message.setSubject("註冊成功!");
        message.setText("使用者 " + username + ", 恭喜您成功註冊!5team");

        mailSender.send(message);
    }
}
