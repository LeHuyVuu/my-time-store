package com.boboibo.mytimestore.service;


import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
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
import org.springframework.http.HttpStatus;
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

//
//    public String refreshToken(@Valid String refreshToken) {
//
//        verifyRefreshToken(refreshToken);
//
//        JWTUtilsHelper util = new JWTUtilsHelper();
//        return util.generateToken(refreshToken);
//    }
public boolean checkLogin(LoginRequest loginRequest) {

    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user == null || !user.isStatus()) {
        throw new AppException(ErrorCode.INVALID_LOGIN);
    }
    boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

    if (!authenticated) {
        throw new AppException(ErrorCode.INVALID_LOGIN);
    }
    return true;
}
}



