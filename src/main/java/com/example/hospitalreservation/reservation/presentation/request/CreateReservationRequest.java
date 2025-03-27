package com.example.hospitalreservation.reservation.presentation.request;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationTime
) {

    public CreateReservationCommand toCommand() {
        validateReservationTime(reservationTime);
        return new CreateReservationCommand(doctorId, patientId, reservationTime);
    }

    private void validateReservationTime(LocalDateTime reservationTime) {
        if (!isWithinBusinessHours(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }

        if (!isHourlySlot(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_TIME_INTERVAL);
        }
    }

    private boolean isWithinBusinessHours(LocalTime time) {
        return !time.isBefore(LocalTime.of(9, 0)) && !time.isAfter(LocalTime.of(16, 59));
    }

    private boolean isHourlySlot(LocalTime time) {
        return time.getMinute() == 0;
    }
}
