package com.example.demo.exception;

import com.example.demo.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseModel> handleCustomException(CustomException ex) {
        ResponseModel errorResponse = new ResponseModel(ex.getStatus(), ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseModel> handleCustomJwtException(IOException ex) {
        ResponseModel errorResponse = new ResponseModel(108, ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
