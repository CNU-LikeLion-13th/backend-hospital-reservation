package com.example.hospitalreservation.reservation.application.command;

public record DeleteReservationCommand(
        Long id,
        String cancelReason
) {
}
