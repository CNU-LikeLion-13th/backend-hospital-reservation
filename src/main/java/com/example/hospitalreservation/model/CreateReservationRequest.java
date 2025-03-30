package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;
    private String reason;

    public CreateReservationRequest(Long doctorId, Long patientId, LocalDateTime reservationTime, String reason) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
        this.reason = reason;
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

    public String getReason() {
        return reason;
    }
}
