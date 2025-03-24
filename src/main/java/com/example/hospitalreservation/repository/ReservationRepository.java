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
        Reservation savedReservation = new Reservation(nextId++, reservation.getDoctorId(), reservation.getPatientId(), reservation.getReservationTime());
        reservations.add(savedReservation);
        return savedReservation;
    }

    public void deleteById(Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
        return;
    }
}
