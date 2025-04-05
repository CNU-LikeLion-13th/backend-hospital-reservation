package com.example.hospitalreservation.model;

import com.example.hospitalreservation.RequestDTO;
import java.time.LocalDateTime;

public class Reservation {
    public Long id;
    public Long doctorId;
    public Long patientId;
    public LocalDateTime reservationTime;
    public LocalDateTime reservationEndTime;
    public String reason;

    public Reservation() {}

    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime, LocalDateTime reservationEndTime, String reason) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
    }
}
