package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;
    private Long fee;

    public Reservation(Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String reason) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
    }

    public Long getId() {return id;}
    public Long getDoctorId() {return doctorId;}
    public Long getPatientId() {return patientId;}
    public LocalDateTime getReservationStartTime() {return reservationStartTime;}
    public LocalDateTime getReservationEndTime() {return reservationEndTime;}
    public String getReason() {return reason;}
    public Long getFee() {return fee;}

    public void setId(Long id) {this.id = id;}
    public void setFee(Long fee) {this.fee = fee;}
}
