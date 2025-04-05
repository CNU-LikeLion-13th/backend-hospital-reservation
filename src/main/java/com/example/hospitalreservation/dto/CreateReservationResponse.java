package com.example.hospitalreservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateReservationResponse {
    private Long reservationId;
    private String message;
    private Long calculatedFee;
    private String error;

    public Long getCalculatedFee() {return calculatedFee;}
    public String getError() {return error;}
    public Long getReservationId() {return reservationId;}
    public String getMessage() {return message;}

    private CreateReservationResponse(Long reservationId, String message, Long calculatedFee, String error) {
        this.reservationId = reservationId;
        this.message = message;
        this.calculatedFee = calculatedFee;
        this.error = error;
    }

    public static CreateReservationResponse success(Long reservationId, String message, Long calculatedFee) {
        return new CreateReservationResponse(reservationId, message, calculatedFee, null);
    }

    public static CreateReservationResponse failure(String errorMessage) {
        return new CreateReservationResponse(null, null, null, errorMessage);
    }
}
