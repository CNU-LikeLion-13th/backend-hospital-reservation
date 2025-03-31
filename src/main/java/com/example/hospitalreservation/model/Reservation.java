package com.example.hospitalreservation.model;

import com.example.hospitalreservation.dto.ReservationDto;

import java.time.LocalDateTime;

public class Reservation {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    // TODO : 필요한 메서드가 있다면 작성해주세요.
    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public ReservationDto getReservationDto() {
        return new ReservationDto(id, doctorId, patientId, reservationTime);
    }
}
