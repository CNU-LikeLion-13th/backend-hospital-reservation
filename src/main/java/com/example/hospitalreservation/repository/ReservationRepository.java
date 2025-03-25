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
        return List.copyOf(reservations);
    }

    // TODO : 예약 엔티티를 저장하는 코드를 작성해주세요.
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

    // TODO : 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public boolean deleteById(Long id) {
//        Reservation toRemove = reservations.stream()
//                .filter(r -> r.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("삭제할 예약이 존재하지 않습니다. ID: %d".formatted(id)));

        return reservations.removeIf(
                reservation -> reservation.getId().equals(id)
        );
    }
}
