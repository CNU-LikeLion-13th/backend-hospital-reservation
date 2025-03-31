package com.example.hospitalreservation.reservation.domain.repository;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public Reservation findById(Long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApplicationException(ReservationExceptionCode.RESERVATION_NOT_FOUND));
    }

    public List<Reservation> findAll() {
        return List.copyOf(reservations);
    }

    public boolean existsByReservationTime(LocalDateTime reservationTime) {
        return reservations.stream()
                .anyMatch(reservation -> reservation.getReservationTime().equals(reservationTime));
    }

    public Reservation save(Reservation reservation) {
        Reservation toSave = new Reservation(
                nextId++,
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationTime()
        );
        reservations.add(toSave);
        return reservation;
    }

    public boolean deleteById(Long id) {
        return reservations.removeIf(
                reservation -> reservation.getId().equals(id)
        );
    }
}
