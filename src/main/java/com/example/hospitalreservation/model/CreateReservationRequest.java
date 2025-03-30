package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    public CreateReservationRequest(Long doctorId, Long patientId, LocalDateTime reservationTime) {
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
