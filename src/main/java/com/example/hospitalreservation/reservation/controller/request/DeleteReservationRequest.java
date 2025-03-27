package com.example.hospitalreservation.reservation.controller.request;

import com.example.hospitalreservation.reservation.service.command.DeleteReservationCommand;

public record DeleteReservationRequest(
        Long id,
        String cancelReason
) {

    public static DeleteReservationCommand toCommand(Long id, String cancelReason) {
        return new DeleteReservationCommand(id, cancelReason);
    }
}
