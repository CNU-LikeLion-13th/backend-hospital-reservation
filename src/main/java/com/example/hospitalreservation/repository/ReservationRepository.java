package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    // TODO_w2 : 모든 예약 엔티티를 조회하는 코드를 작성해주세요.
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations);
    }

    // TODO_w2 : 예약 엔티티를 저장하는 코드를 작성해주세요.
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