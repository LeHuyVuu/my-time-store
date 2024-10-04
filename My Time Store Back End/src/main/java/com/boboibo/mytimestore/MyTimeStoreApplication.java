package com.boboibo.mytimestore;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class MyTimeStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyTimeStoreApplication.class, args);
    }
}
