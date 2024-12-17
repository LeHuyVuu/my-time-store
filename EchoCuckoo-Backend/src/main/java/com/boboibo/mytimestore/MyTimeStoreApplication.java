package com.boboibo.mytimestore;

import com.boboibo.mytimestore.service.SendEmailService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class MyTimeStoreApplication {
    @Autowired
    SendEmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(MyTimeStoreApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail() {
//        emailService.sendMailSender(
//                "trankhang0990@gmail.com",
//                "This is subject",
//                "This is Body Of Email");
//    }
}
