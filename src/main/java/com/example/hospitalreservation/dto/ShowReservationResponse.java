package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowReservationResponse {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;
    private Long fee;

    public Long getId() {return id;}
    public Long getDoctorId() {return doctorId;}
    public Long getPatientId() {return patientId;}
    public LocalDateTime getReservationStartTime() {return reservationStartTime;}
    public LocalDateTime getReservationEndTime() {return reservationEndTime;}
    public String getReason() {return reason;}
    public Long getFee() {return fee;}
    
    private ShowReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.doctorId = reservation.getDoctorId();
        this.patientId = reservation.getPatientId();
        this.reservationStartTime = reservation.getReservationStartTime();
        this.reservationEndTime = reservation.getReservationEndTime();
        this.reason = reservation.getReason();
        this.fee = reservation.getFee();
    }

    public static ShowReservationResponse from(Reservation reservation) {
        return new ShowReservationResponse(reservation);
    }
}
