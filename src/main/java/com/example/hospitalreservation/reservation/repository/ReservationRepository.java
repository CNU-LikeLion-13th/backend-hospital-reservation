package com.example.hospitalreservation.reservation.repository;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public Reservation findById(Long id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                return reservation;
            }
        }
        throw new ApplicationException(ReservationExceptionCode.RESERVATION_NOT_FOUND);
    }

    public List<Reservation> findAll() {
        return List.copyOf(reservations);
    }

    public boolean existsByReservationTime(LocalDateTime reservationTime) {
        return reservations.stream().anyMatch(reservation -> {
            LocalDateTime startTime = reservation.getReservationTime();
            LocalDateTime endTime = startTime.plusHours(1);

            return !reservationTime.isBefore(startTime) && reservationTime.isBefore(endTime);
        });
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
