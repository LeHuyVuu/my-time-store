package com.boboibo.mytimestore.model.request;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    Long userId;
    String fullName;
    String email;
    String phoneNumber;
    String address;
    String image;

}
