package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
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
