package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends Exception {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

    private HttpStatus httpStatus;

    public CustomException() {

    }

    public CustomException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomException(int status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CustomException(int status, String message, Object data, HttpStatus httpStatus) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
    }
}
