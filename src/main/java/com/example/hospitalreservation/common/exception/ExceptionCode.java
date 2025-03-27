package com.example.hospitalreservation.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    String getName();

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();
}
