package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
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
        // TODO: 존재하지 않는 예약을 취소하려 하면 예외처리
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }
}
