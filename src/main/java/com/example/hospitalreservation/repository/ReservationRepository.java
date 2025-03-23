package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;

    // TODO : 모든 예약 엔티티를 조회하는 코드를 작성해주세요.
    public List<Reservation> findAll() {
        return null;
    }

    // TODO : 예약 엔티티를 저장하는 코드를 작성해주세요.
    public Reservation save(Reservation reservation) {
        return null;
    }

    // TODO : 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public void deleteById(Long id) {
        return;
    }
}
