package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    public ReservationDTO() {} // 데이터 바인딩 목적

    public ReservationDTO(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static ReservationDTO fromReservation(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationTime()
        );
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

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
