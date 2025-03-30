package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    public ReservationDTO(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static ReservationDTO fromEntity(Reservation reservation) {
        return new ReservationDTO(reservation.getId(),
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
