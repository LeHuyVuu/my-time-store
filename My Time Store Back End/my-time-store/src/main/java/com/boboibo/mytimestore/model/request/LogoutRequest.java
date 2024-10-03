package com.boboibo.mytimestore.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class LogoutRequest {
    private String token;
}
