package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    // TODO : 모든 예약 엔티티를 조회하는 코드를 작성해주세요.
    public List<Reservation> findAll() {
        return reservations;
    }

    // TODO : 예약 엔티티를 저장하는 코드를 작성해주세요.
    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);
        return reservation;
    }

    // TODO : 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public void deleteById(Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }

    public Optional<Reservation> findByReservationTime(LocalDateTime dateTime) {
        return reservations.stream()
                .filter(r -> r.getReservationTime().equals(dateTime))
                .findFirst();
    }

    public Optional<Reservation> findById(Long id) {
        return reservations.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

}
