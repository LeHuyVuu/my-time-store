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

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody UserRequest newUser) {
        try {
            if (userService.isUserExists(newUser.getUsername())) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                        new ResponseObject("Failed", "User name already exists!", "")
                );
            }
            userService.createUser(newUser);
            return ResponseObject.APIRepsonse("200","User added successfully!",HttpStatus.CREATED,newUser);
        } catch (Exception e) {
            return ResponseObject.APIRepsonse("Error","Error",HttpStatus.BAD_REQUEST,"");
        }
    }

    @GetMapping("")
    public List<User> getListUser() {
        return userService.getListUser();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable @Valid String username) {
        User user = userService.getUserByUsername(username);
        if (user != null && user.isStatus()) {
            userService.deleteByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "User deleted successfully!", user)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "User does not exist!", "")
            );
        }
    }

    @GetMapping("/myInfo")
    public ResponseEntity<ResponseObject> getMyInfo(HttpServletRequest request) {
        User myInfo = userService.getMyInfo(request);
        if (myInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "User info retrieved successfully!", myInfo)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "User does not exist!", "")
            );
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody UpdateUserRequest newUser) {
        User user = userService.getUserByUsername(newUser.getUsername());
        if (user != null && user.isStatus()) {
           User userUpdated =  userService.updateUser(newUser, user.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "User deleted successfully!", userUpdated)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "User does not exist!", "")
            );
        }
    }


    @PostMapping("/updatePassword")
    public ResponseEntity<ResponseObject> updatePassword(HttpServletRequest request, @RequestBody UpdatePasswordRequest passwordRequest) {
          User user = userService.getMyInfo(request);
          boolean check = userService.updatePassword(user, passwordRequest);
        if(check) {
            return APIRepsonse("200", " ", HttpStatus.OK, "");
        } else if (Objects.equals(passwordRequest.getCurrentPassword(), passwordRequest.getNewPassword())) {
           return  APIRepsonse("Failed", "Current pass must be different from current", HttpStatus.BAD_REQUEST, "");
        } else{
            return APIRepsonse("Failed", "Your current password is incorrect ", HttpStatus.BAD_REQUEST, "");
        }
    }
}
