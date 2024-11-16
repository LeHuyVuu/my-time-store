package com.boboibo.mytimestore.repository;

import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @MockBean
    private UserRepository userRepository;
}