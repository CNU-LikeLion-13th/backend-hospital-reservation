package com.example.hospitalreservation.reservation.service.command;

import com.example.hospitalreservation.reservation.domain.Reservation;

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
