package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private static long ID_SEQ = 1;
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    private Reservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = ID_SEQ++;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static Reservation of(Long doctorId, Long patientId, LocalDateTime reservationTime){
        return new Reservation(doctorId,patientId,reservationTime);
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
