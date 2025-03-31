package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public class ReservationDto {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    public ReservationDto(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
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
