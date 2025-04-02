package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation  { //implements TimeEntity
    public Long id;
    public Long doctorId;
    public Long patientId;
    public LocalDateTime reservationTime;

    // TODO : 필요한 메서드가 있다면 작성해주세요.
    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

}
