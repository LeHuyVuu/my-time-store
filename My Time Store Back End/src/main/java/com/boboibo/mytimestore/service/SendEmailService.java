package com.boboibo.mytimestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")  // Email người gửi được cấu hình trong application.properties
    private String fromEmailId;

    public boolean sendMailSender(String recipient, String body, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmailId);  // Người gửi
            message.setTo(recipient);      // Người nhận
            message.setSubject(subject);   // Chủ đề email
            message.setText(body);         // Nội dung email
            mailSender.send(message);      // Thực hiện gửi email
            return true;
        } catch (Exception e) {
            System.out.println(e);  // Để xem thông tin lỗi chi tiết trong log
            return false;
        }
    }
}
