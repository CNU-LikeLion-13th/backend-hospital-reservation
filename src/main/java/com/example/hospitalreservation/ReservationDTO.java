package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Reservation;
import java.time.LocalDateTime;

public class ReservationDTO {
    public final Long id;
    public final Long doctorId;
    public final Long patientId;
    public final LocalDateTime reservationTime;

    public ReservationDTO(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public static ReservationDTO from(Reservation reservation) {
        return new ReservationDTO(
                reservation.id,
                reservation.doctorId,
                reservation.patientId,
                reservation.reservationTime
        );
    }
}
