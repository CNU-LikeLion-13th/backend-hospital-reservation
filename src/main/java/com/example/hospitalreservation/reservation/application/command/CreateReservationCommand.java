package com.example.hospitalreservation.reservation.application.command;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationTime
) {

    public Reservation toReservation() {
        return new Reservation(null, doctorId, patientId, reservationTime);
    }
}
