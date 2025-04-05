package com.example.hospitalreservation.enums;

public enum ReservationException
{
    NOT_AVAILABLE_TIME("의사의 진료 가능 시간 (09:00~17:00) 내에서만 예약할 수 있습니다."),
    RESERVATION_TIME_ALREADY_EXISTS("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요."),
    NOT_EXIST_RESERVATION("존재하지 않는 예약입니다.");

    private final String errorMessage;

    public String getErrorMessage(){ return errorMessage; }

    ReservationException(String error){
        this.errorMessage = error;
    }
}
