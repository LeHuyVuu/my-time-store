package com.boboibo.mytimestore.model.response.user;

import com.boboibo.mytimestore.model.enums.Role;
import com.boboibo.mytimestore.model.response.customer.CustomerResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserResponse {
    Long userId;
    String username;
    String email;
    String fullName;
    String image;
    Role role;
    boolean status;
    CustomerResponse customerResponse ;

}
