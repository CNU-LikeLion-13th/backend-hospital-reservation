package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;

    public CreateReservationRequest(Long patientId,
                                    Long doctorId,
                                    LocalDateTime reservationStartTime,
                                    LocalDateTime reservationEndTime,
                                    String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public String getReason() {
        return reason;
    }
}