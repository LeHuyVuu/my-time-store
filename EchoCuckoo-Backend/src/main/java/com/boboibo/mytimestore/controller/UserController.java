package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.UpdatePasswordRequest;
import com.boboibo.mytimestore.model.request.UpdateUserRequest;
import com.boboibo.mytimestore.model.request.UserRequest;
import com.boboibo.mytimestore.model.request.authentication.RegisterRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.user.UserResponse;
import com.boboibo.mytimestore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;
    // them,sua,xoa,update
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterRequest newUser) {
        try {
            if (userService.getUserByUsername(newUser.getUsername()) || userService.getUserByEmail(newUser.getEmail())) {
                return ResponseObject.APIRepsonse(409, "Username or email already exists", HttpStatus.CONFLICT, "");
            }else {
                userService.createCustomer(newUser);
                return ResponseObject.APIRepsonse(200, "Register successfully!", HttpStatus.CREATED, "");
            }
        } catch (Exception e) {
            return ResponseObject.APIRepsonse(500, "An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseObject> update(@RequestBody UpdateUserRequest updateUserRequest){
        UserResponse isUpdated = userService.updateUser(updateUserRequest);
        if(isUpdated != null){
            return ResponseObject.APIRepsonse(200, "User updated successfully!", HttpStatus.OK, isUpdated);
        }else {
            return ResponseObject.APIRepsonse(500, "Update fail!", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteUser(@RequestParam Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseObject.APIRepsonse(200, "User deleted successfully!", HttpStatus.OK, null);
        } else {
            return ResponseObject.APIRepsonse(404, "User does not exist!", HttpStatus.NOT_FOUND, null);
        }
    }
    @PostMapping("/myInfo")
    public ResponseEntity<ResponseObject> getMyInfo(HttpServletRequest request) {
        UserResponse myInfo = userService.getMyInfo(request);
        if (myInfo != null) {
            return ResponseObject.APIRepsonse(200, "User info retrieved successfully!", HttpStatus.OK, myInfo);
        } else {
            return ResponseObject.APIRepsonse(404, "User does not exist!", HttpStatus.NOT_FOUND, "");
        }
    }


}
