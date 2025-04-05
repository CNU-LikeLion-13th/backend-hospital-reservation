package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Reservation;
import java.time.LocalDateTime;

public class RequestDTO { //예약을 생성할 때 값을 옮겨주는 DTO
    private final Long patientId;
    private final Long doctorId;
    private final LocalDateTime reservationStartTime;
    private final LocalDateTime reservationEndTime;
    private final String reason;

    public RequestDTO() {
        this.patientId = null;
        this.doctorId = null;
        this.reservationStartTime = null;
        this.reservationEndTime = null;
        this.reason = null;
    }

    public RequestDTO(Long patientId, Long doctorId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String reason) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
    }

    private Long getPatientId() {
        return patientId;
    }

    private Long getDoctorId() {
        return doctorId;
    }

    private LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    private LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    private String getReason() {
        return reason;
    }

    public Reservation toReservation(Long id) {
        return new Reservation(
                id,
                getDoctorId(),
                getPatientId(),
                getReservationStartTime(),
                getReservationEndTime(),
                getReason()
        );
    }
}
