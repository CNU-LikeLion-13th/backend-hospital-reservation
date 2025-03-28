package com.example.hospitalreservation.reservation.exception;

import com.example.hospitalreservation.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;

public enum ReservationExceptionCode implements ExceptionCode {

    OUT_OF_BUSINESS_HOURS(HttpStatus.BAD_REQUEST, "R000", "예약 가능한 시간은 09:00 ~ 17:00 입니다."),
    DUPLICATE_RESERVATION_TIME(HttpStatus.CONFLICT, "R001", "해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해 주세요."),
    INVALID_RESERVATION_TIME_RANGE(HttpStatus.BAD_REQUEST, "R002", "예약은 1시간 단위(예: 09:00, 10:00)로만 가능합니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NO_CONTENT, "R003", "존재하지 않는 예약입니다."),
    INVALID_RESERVATION_TIME_PAST(HttpStatus.BAD_REQUEST, "R004", "예약 시간이 현재 시간보다 이전일 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ReservationExceptionCode(HttpStatus httpStatus, String code, String message) {
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
