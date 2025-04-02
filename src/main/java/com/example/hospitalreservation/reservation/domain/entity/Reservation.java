package com.example.hospitalreservation.reservation.domain.entity;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservation {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    public Reservation(
            final Long id,
            final Long doctorId,
            final Long patientId,
            final LocalDateTime reservationTime
    ) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public void validateWithinBusinessHours(LocalDateTime time) {
        LocalTime localTime = time.toLocalTime();
        if (localTime.isBefore(LocalTime.of(9, 0)) || !localTime.isAfter(LocalTime.of(16, 59))) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }
    }

    public void validateHourlySlot(LocalDateTime time) {
        if (!(time.getMinute() == 0)) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }

    public boolean isOverlapping(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime startTime = existingTime;
        LocalDateTime endTime = startTime.plusHours(1);
        return !newTime.isBefore(startTime) && newTime.isBefore(endTime);
    }

    public Long getId() {
        return id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
