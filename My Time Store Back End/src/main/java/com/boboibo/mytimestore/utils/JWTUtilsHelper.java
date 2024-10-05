package com.boboibo.mytimestore.utils;


import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.repository.LoggedOutTokenRepository;
import com.boboibo.mytimestore.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtilsHelper {

    @Autowired
    LoggedOutTokenRepository  loggedOutTokenRepository;
    @Autowired
    UserRepository userRepository;

        @Value("${myapp.api-key}")
        private String privateKey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        User user = userRepository.findUsersByUsername(data);

        String jws = Jwts.builder().subject(data)
                .claim("userid", user.getUserId())
                .claim("role", user.getRoles())
                .issuer("MyTimeStore.com")
                .issuedAt(new Date())
                .claim("jti", UUID.randomUUID().toString())
                .expiration(new Date(
                        Instant.now().plus(2, ChronoUnit.DAYS).toEpochMilli()
                ))
                .signWith(key).compact();
        return jws;
    }

    public boolean verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
           Jwts.parser() // Use parserBuilder() instead of parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true; // Return true if the token is valid
        } catch (Exception e) {
            return false; // Return false if the token verification fails
        }
    }

    public boolean isTokenLoggedOut(String token) {
        return loggedOutTokenRepository.findByToken(token).isPresent();
    }





}
