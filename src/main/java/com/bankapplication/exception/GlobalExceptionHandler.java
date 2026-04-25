package com.bankapplication.exception;

import com.bankapplication.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Login Related Exception
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuth(AuthException ex) {
        return ResponseEntity
                .status(401)
                .body(new ErrorResponse(ex.getMessage()));
    }

    //Account Related Exceptions
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorResponse> handleAccount(AccountException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }


    //Other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}