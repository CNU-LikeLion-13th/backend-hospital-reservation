package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime reservationTime;

    public CreateReservationRequest(Long patientId, Long doctorId, LocalDateTime reservationTime) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reservationTime = reservationTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}