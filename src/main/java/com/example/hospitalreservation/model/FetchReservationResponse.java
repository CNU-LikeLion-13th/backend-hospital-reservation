package com.example.hospitalreservation.model;

import com.example.hospitalreservation.domain.Reservation;

import java.time.LocalDateTime;

public class FetchReservationResponse {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Integer fee;

    private FetchReservationResponse(Long id, Long doctorId, Long patientId,
                                     LocalDateTime reservationStartTime, LocalDateTime reservationEndTime,
                                     Integer fee) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.fee = fee;
    }

    public static FetchReservationResponse from(Reservation reservation) {
        return new FetchReservationResponse(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getFee());
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

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public Integer getFee() {
        return fee;
    }
}

