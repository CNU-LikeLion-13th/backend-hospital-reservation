package com.example.hospitalreservation.domain;

import com.example.hospitalreservation.model.CreateReservationRequest;
import com.example.hospitalreservation.service.TimeEntity;

import java.time.LocalDateTime;

public class Reservation implements TimeEntity {
    private static final int RESERVATION_END_HOURS = 1;

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    private Reservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static Reservation of(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        return new Reservation(doctorId, patientId, reservationTime);
    }

    public static Reservation from(CreateReservationRequest request) {
        return new Reservation(
                request.getDoctorId(),
                request.getPatientId(),
                request.getReservationTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public LocalDateTime getStartTime() {
        return reservationTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return reservationTime.plusHours(RESERVATION_END_HOURS);
    }
}
