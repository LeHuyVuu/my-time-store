package com.boboibo.mytimestore.service;


import com.boboibo.mytimestore.model.entity.LoggedOutToken;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.LoginRequest;
import com.boboibo.mytimestore.model.request.LogoutRequest;

import com.boboibo.mytimestore.repository.LoggedOutTokenRepository;
import com.boboibo.mytimestore.repository.UserRepository;
import com.boboibo.mytimestore.utils.JWTUtilsHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthenticateService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${myapp.api-key}")
    private String privateKey;
    @Autowired
    private LoggedOutTokenRepository loggedOutTokenRepository;


    public boolean checkLogin(@Valid LoginRequest loginRequest) {
        // Tìm người dùng theo username
        User user = userRepository.findUsersByUsername(loginRequest.getUsername());
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        // Kiểm tra mật khẩu
        boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AuthenticationFailedException("Wrong password");
        }
        return true;
    }

    public void logout(String token) {
        LoggedOutToken loggedOutToken = new LoggedOutToken(token, new Date());
        loggedOutTokenRepository.save(loggedOutToken);
    }



    private Jws<Claims> verifyToken(LogoutRequest token) throws JwtException {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));

            // Sử dụng parserBuilder thay vì parser
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.getToken());

            return jws; // Trả về Jws<Claims> nếu token hợp lệ
        } catch (JwtException e) {
            throw new JwtException("Token verification failed", e); // Ném ngoại lệ nếu token không hợp lệ
        }
    }

    private void verifyRefreshToken(String token) throws JwtException {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));

            // Sử dụng parserBuilder thay vì parser
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (JwtException e) {
            throw new JwtException("Token verification failed", e); // Ném ngoại lệ nếu token không hợp lệ
        }
    }


    public String refreshToken(@Valid String refreshToken) {

        verifyRefreshToken(refreshToken);

        JWTUtilsHelper util = new JWTUtilsHelper();
        return util.generateToken(refreshToken);
    }
}

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}

