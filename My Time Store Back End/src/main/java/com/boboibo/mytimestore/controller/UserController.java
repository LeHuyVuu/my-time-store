package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.UpdatePasswordRequest;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.request.UserRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.boboibo.mytimestore.model.response.ResponseObject.APIRepsonse;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")

public class UserController {

}
