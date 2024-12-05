package com.boboibo.mytimestore.exception;


import com.boboibo.mytimestore.model.response.ResponseObject;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Xử lý ngoại lệ chung
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseObject> handlingRuntimeException(Exception exception) {
        log.error("Exception: ", exception);

        // Trả về ResponseObject với mã lỗi UNCATEGORIZED_EXCEPTION
        ResponseObject responseObject = new ResponseObject(
                ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage(),
                null // Không có dữ liệu trả về cho lỗi này
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
    }

    // Xử lý AppException tùy chỉnh (nếu có)
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseObject> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        // Tạo ResponseObject từ ErrorCode
        ResponseObject responseObject = new ResponseObject(
                errorCode.getCode(),
                errorCode.getMessage(),
                null // Có thể tùy chỉnh nếu cần thêm dữ liệu
        );

        return ResponseEntity.status(errorCode.getStatusCode()).body(responseObject);
    }

    // Xử lý AccessDeniedException (lỗi không có quyền truy cập)
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ResponseObject> handlingAccessDeniedException(AccessDeniedException exception) {
        log.error("Access Denied: ", exception);

        // Trả về ResponseObject với mã lỗi UNAUTHORIZED
        ResponseObject responseObject = new ResponseObject(
                ErrorCode.UNAUTHORIZED.getCode(),
                ErrorCode.UNAUTHORIZED.getMessage(),
                null // Không có dữ liệu trả về cho lỗi này
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseObject);
    }

    // Xử lý lỗi khi đối tượng không hợp lệ (MethodArgumentNotValidException)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseObject> handlingValidationException(MethodArgumentNotValidException exception) {
        log.error("Validation Error: ", exception);

        // Trả về ResponseObject với mã lỗi INVALID_KEY
        ResponseObject responseObject = new ResponseObject(
                ErrorCode.INVALID_KEY.getCode(),
                ErrorCode.INVALID_KEY.getMessage(),
                null // Không có dữ liệu trả về cho lỗi này
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
    }
}
