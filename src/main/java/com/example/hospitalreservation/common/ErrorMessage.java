package com.example.hospitalreservation.common;

public enum ErrorMessage {
    FAIL_CREATE("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요."),
    FAIL_DELETE("존재하지 않는 예약입니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
