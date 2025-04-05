package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public class CreateReservationRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;

    public Long getDoctorId() {return doctorId;}
    public Long getPatientId() {return patientId;}
    public LocalDateTime getReservationStartTime() {return reservationStartTime;}
    public LocalDateTime getReservationEndTime() {return reservationEndTime;}
    public String getReason() {return reason;}
}
