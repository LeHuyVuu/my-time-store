package com.boboibo.mytimestore.model.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain spaces or special characters")
    String username;

    @Size(min = 2, max = 50, message = "Fullname must be between 2 and 50 characters")
    String fullname;
    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid and between 10-15 digits")
    String phone;
}
