package com.boboibo.mytimestore.model.request.authentication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RegisterRequest {
    String email;
    String password;
    String username;
    String fullname;
    String address;
    String phone;
    String image;
}
