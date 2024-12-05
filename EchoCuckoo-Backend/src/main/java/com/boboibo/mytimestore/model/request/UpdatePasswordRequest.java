package com.boboibo.mytimestore.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {
    // Getters and Setters
    private String currentPassword;
    private String newPassword;

}
