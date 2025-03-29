package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepository {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    Reservation r = new Reservation(id, doctorId, patientId, reservationTime);
    private final List<Reservation> reservations = new ArrayList<>();
    private Long nextId = 1L;


    // TODO : 모든 예약 엔티티를 조회하는 코드를 작성해주세요.
    public List<Reservation> findAll() {
        return reservations;
    }

    // TODO : 예약 엔티티를 저장하는 코드를 작성해주세요.
    public Reservation save(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    // TODO : 예약 엔티티를 삭제하는 코드를 작성해주세요.
    public void deleteById(Long id) {
        for(int i = reservations.size() - 1; i >= 0; i--){
            if (reservations.get(i).equals(id)) {
                reservations.remove(i);
            }
        }
        return;
    }
}
