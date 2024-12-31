package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.LoginRequest;
import com.boboibo.mytimestore.model.request.LogoutRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.AuthenticateService;
import com.boboibo.mytimestore.service.UserService;
import com.boboibo.mytimestore.utils.JWTUtilsHelper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.boboibo.mytimestore.model.response.ResponseObject.APIRepsonse;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtilsHelper jwtUtilsHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        if (authenticateService.checkLogin(loginRequest)) {
            String token = jwtUtilsHelper.generateToken(loginRequest.getUsername());
            return APIRepsonse(200, "Login Successfully", HttpStatus.OK, token);
        } else {
            return APIRepsonse(403, "Login Failed", HttpStatus.UNAUTHORIZED, "");
        }
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
//        // Lưu token vào database
//        authenticateService.logout(logoutRequest.getToken());
//
//        // Thực hiện các hành động logout khác nếu cần
//        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
//    }

}




