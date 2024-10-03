package com.boboibo.mytimestore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject {
    String status;
    String message;
    Object data;


    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseEntity<ResponseObject> APIRepsonse(String status, String message, HttpStatus httpStatus, Object data) {
        return ResponseEntity.status(httpStatus).body(
                new ResponseObject(status, message, data)
        );
    }
}

