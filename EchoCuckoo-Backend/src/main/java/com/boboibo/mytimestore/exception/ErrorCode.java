package com.boboibo.mytimestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    DATABASE_EXCEPTION(1009, "Database error", HttpStatus.INTERNAL_SERVER_ERROR),
    NEWS_NOT_EXIST(1010, "News not exist", HttpStatus.NOT_FOUND),
    USER_NOT_EXIST(1011, "User not existed", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1012,"Invalid token",HttpStatus.FORBIDDEN),
    CART_NOT_EXIST(1013, "Cart not exist", HttpStatus.NOT_FOUND),
    CART_NOT_ADD(1014, "Cart not add", HttpStatus.NOT_FOUND),
    PRODUCT_ID_NOT_EXIST(1015, "Product id not exist", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_EXIST(1016, "Cart item not exist", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_EXIST_BY_USERID(1017, "No cart item exists for the given user ID", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_EXIST(1018, "Customer not exist", HttpStatus.NOT_FOUND),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}