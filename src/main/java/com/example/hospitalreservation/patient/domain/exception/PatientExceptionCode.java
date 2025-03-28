package com.example.hospitalreservation.patient.domain.exception;

import com.example.hospitalreservation.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

public enum PatientExceptionCode implements ExceptionCode {

    INVALID_POSITIVE_PATIENT_ID(HttpStatus.BAD_REQUEST, "P000", "환자 ID는 양수이어야 합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    PatientExceptionCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
