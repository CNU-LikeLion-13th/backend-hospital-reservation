package com.example.hospitalreservation.model;

public enum SuccessMessage {

    CREATE_RESERVATION("예약이 완료되었습니다."),
    DELETE_RESERVATION("예약이 취소되었습니다."),
    ;

    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
