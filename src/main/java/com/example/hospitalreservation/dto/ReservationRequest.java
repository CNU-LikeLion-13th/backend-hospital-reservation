package com.example.hospitalreservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ReservationRequest {
    @JsonProperty("patientId")
    private Long patientId;

    @JsonProperty("doctorId")
    private Long doctorId;

    @JsonProperty("reservationTime")
    private LocalDateTime reservationTime;

    @JsonProperty("reason")
    private String reason;

    public ReservationRequest() {}

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public String getReason() {
        return reason;
    }
}
