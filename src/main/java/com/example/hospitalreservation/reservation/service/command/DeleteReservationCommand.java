package com.example.hospitalreservation.reservation.service.command;

public record DeleteReservationCommand(
        Long id,
        String cancelReason
) {
}
