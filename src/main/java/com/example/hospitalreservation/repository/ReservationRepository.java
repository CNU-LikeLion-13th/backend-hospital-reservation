package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.enums.ReservationException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public Long getNextId() { return nextId; }

    public List<Reservation> findAll() {
        return reservations;
    }

    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);
        return reservation;
    }

    public void deleteById(Long id) throws Exception
    {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                reservations.remove(reservation);
                return;
            }
        }
        throw new Exception(ReservationException.NOT_EXIST_RESERVATION.getErrorMessage());
    }
}
