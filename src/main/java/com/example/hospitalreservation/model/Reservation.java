package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    // TODO : 필요한 메서드가 있다면 작성해주세요.
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public Reservation() {
    }

    //생성자 추가
    public Reservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

}
