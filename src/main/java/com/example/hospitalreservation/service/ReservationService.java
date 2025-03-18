package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@RequiredArgsConstructor
@Service
public class ReservationService {

    // TODO : 주입 받아야 객체를 작성해주세요.
    private final ReservationRepository reservationRepository;

    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<Reservation> getAllReservations() {
        return null;
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        Reservation reservation = new Reservation(null, doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id) {
        return;
    }
}
