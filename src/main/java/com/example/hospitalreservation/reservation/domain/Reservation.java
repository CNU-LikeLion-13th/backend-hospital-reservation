package com.example.hospitalreservation.reservation.domain;

import java.time.LocalDateTime;

public class Reservation {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    public Reservation(
            final Long id,
            final Long doctorId,
            final Long patientId,
            final LocalDateTime reservationTime
    ) {
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
