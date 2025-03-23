package com.example.hospitalreservation.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    // TODO_w2 : 필요한 메서드가 있다면 작성해주세요.
    public void setId(Long id){
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    public Long getDoctorId() {
        return doctorId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public Long getPatientId() {
        return patientId;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
