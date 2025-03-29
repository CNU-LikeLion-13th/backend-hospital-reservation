package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.domain.Reservation;
import org.springframework.stereotype.Repository;

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
        boolean isRemoved = reservations.removeIf(reservation -> reservation.getId().equals(id));

        if (!isRemoved) {
            throw new IllegalArgumentException("존재하지 않는 예약입니다.");
        }
    }
}
