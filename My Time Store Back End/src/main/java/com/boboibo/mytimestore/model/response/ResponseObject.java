package com.boboibo.mytimestore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject {
    int status;
    String message;
    Object data;

    public static ResponseEntity<ResponseObject> APIRepsonse(int status, String message, HttpStatus httpStatus, Object data) {
        return ResponseEntity.status(httpStatus).body(
                new ResponseObject(status, message, data)
        );
    }
}

