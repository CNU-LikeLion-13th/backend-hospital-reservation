package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public List<Reservation> findAll() {
        return reservations;
    }

    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);
        return reservation;
    }

    public void deleteById(Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public Reservation findById(Long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Reservation reservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(reservation.getId())) {
                reservations.set(i, reservation);
                break;
            }
        }
    }

    public boolean existsByDoctorIdAndReservationTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return reservations.stream()
                .anyMatch(reservation -> reservation.getDoctorId().equals(doctorId) &&
                        !reservation.getReservationTime().isBefore(start) &&
                        reservation.getReservationTime().isBefore(end));
    }
}

