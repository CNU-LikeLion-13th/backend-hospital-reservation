package com.example.hospitalreservation.model;

import java.time.LocalDateTime;
import com.example.hospitalreservation.dto.ReservationDTO;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;
    private int fee;

    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String reason, int fee) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
        this.fee = fee;
    }

    public ReservationDTO toDto() {
        return new ReservationDTO(id, doctorId, patientId, reservationStartTime, reservationEndTime, reason, fee);
    }
}
