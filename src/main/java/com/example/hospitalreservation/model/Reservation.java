package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;
    private final String reason;

    private Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime, String reason) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
        this.reason = reason;
    }

    public static Reservation of(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime, String reason) {
        return new Reservation(id, doctorId, patientId, reservationTime, reason);
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

    public String getReason() {
        return reason;
    }
}
