package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.CustomerMapper;
import com.boboibo.mytimestore.mapper.UserMapper;
import com.boboibo.mytimestore.model.entity.Customer;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.enums.Role;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.request.authentication.RegisterRequest;
import com.boboibo.mytimestore.model.response.user.UserResponse;
import com.boboibo.mytimestore.repository.CustomerRepository;
import com.boboibo.mytimestore.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CustomerService {
CustomerRepository customerRepository;
    public Customer getCustomerByUserId(Long userId) {
        Customer customer = customerRepository.findByUser_UserId(userId);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_EXIST);
        }
        return customer;
    }
}

