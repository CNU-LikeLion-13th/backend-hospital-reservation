package com.example.hospitalreservation.model;

public class ErrorResponse {
    private String message;

    private ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public static ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }
}