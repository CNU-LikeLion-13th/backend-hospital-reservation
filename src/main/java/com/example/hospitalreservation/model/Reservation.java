package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    // TODO_w2 : 필요한 메서드가 있다면 작성해주세요.
    public Reservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    } //refactor: setter 대신 생성자

    public static Reservation of(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        return new Reservation(doctorId, patientId, reservationTime);
    }//정적 팩토리 메소드

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
