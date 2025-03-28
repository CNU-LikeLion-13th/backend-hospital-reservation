package com.example.hospitalreservation.reservation.presentation.request;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.patient.domain.exception.PatientExceptionCode;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public record CreateReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationTime
) {

    public CreateReservationCommand toCommand() {
        validateReservationTime(reservationTime);
        validatePositiveId(patientId);
        return new CreateReservationCommand(doctorId, patientId, reservationTime);
    }

    private void validatePositiveId(Long id) {
        if (!isPositiveId(id)) {
            throw new ApplicationException(PatientExceptionCode.INVALID_POSITIVE_PATIENT_ID);
        }
    }

    private void validateReservationTime(LocalDateTime reservationTime) {
        if (!isTimeBeforeNow(reservationTime)) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_PAST);
        }

        if (!isWithinBusinessHours(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }

        if (!isHourlySlot(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }

    private boolean isPositiveId(Long id) {
        return !(Objects.isNull(id) || id <= 0);
    }

    private boolean isTimeBeforeNow(LocalDateTime time) {
        return !time.isBefore(LocalDateTime.now());
    }

    private boolean isWithinBusinessHours(LocalTime time) {
        return !time.isBefore(LocalTime.of(9, 0)) && !time.isAfter(LocalTime.of(16, 59));
    }

    private boolean isHourlySlot(LocalTime time) {
        return time.getMinute() == 0;
    }
}
