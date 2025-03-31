package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations);
    }

    public Optional<Reservation> findById(Long id) {
        return reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getId(), id))
                .findFirst();
    }

    public Optional<Reservation> findByDoctorIdAndReservationTime(Long doctorId, LocalDateTime reservationTime) {
        return reservations.stream()
                .filter(reservation -> reservation.getDoctorId().equals(doctorId) &&
                        reservation.getReservationTime().equals(reservationTime))
                .findFirst();
    }

    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);

        return reservation;
    }

    // TODO_w2 : 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public void deleteById(Long id) {
        reservations.removeIf(reservation -> Objects.equals(reservation.getId(), id));
    }
}