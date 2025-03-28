package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    private Reservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static Reservation of(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        return new Reservation(doctorId, patientId, reservationTime);
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

    // 삭제하고 싶으나 일단 킵
    public void setId(Long id) {
        this.id = id;
    }
}
