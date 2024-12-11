package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel<T> {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public ResponseModel() {

    }

    public ResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(int status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
