package com.example.hospitalreservation.model;

import com.example.hospitalreservation.common.SuccessMessage;
import com.example.hospitalreservation.domain.Reservation;

public class CreateReservationResponse {
    private Long reservationId;
    private String message;
    private Integer calculatedFee;

    private CreateReservationResponse(Long reservationId, String message, Integer calculatedFee) {
        this.reservationId = reservationId;
        this.message = message;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getMessage() {
        return message;
    }

    public static CreateReservationResponse from(Reservation reservation, SuccessMessage message, Integer calculatedFee) {
        return new CreateReservationResponse(reservation.getId(), message.getMessage(), calculatedFee);
    }
}