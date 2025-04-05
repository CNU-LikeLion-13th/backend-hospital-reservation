package com.example.hospitalreservation.domain;

import com.example.hospitalreservation.model.CreateReservationRequest;
import com.example.hospitalreservation.service.TimeEntity;

import java.time.LocalDateTime;

public class Reservation implements TimeEntity {
    private static final int RESERVATION_END_HOURS = 1;

    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Integer fee;

    private Reservation(Long doctorId,
                        Long patientId,
                        LocalDateTime reservationStartTime,
                        LocalDateTime reservationEndTime,
                        Integer fee) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.fee = fee;
    }

    public static Reservation from(CreateReservationRequest dto, Integer fee) {
        return new Reservation(dto.getDoctorId(),
                dto.getPatientId(),
                dto.getReservationStartTime(),
                dto.getReservationEndTime(),
                fee);
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

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public Integer getFee() {
        return fee;
    }

    @Override
    public LocalDateTime getStartTime() {
        return getReservationStartTime();
    }

    @Override
    public LocalDateTime getEndTime() {
        return getReservationEndTime();
    }
}
