package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// TODO : 서비스 레이어에서 필요한 어노테이션을 작성해주세요.
@Service
public class ReservationService {

    // TODO : 주입 받아야 객체를 작성해주세요.
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // TODO : 모든 예약 리스트를 조회하는 코드를 작성해주세요.
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // TODO : 새로운 예약을 생성하는 코드를 작성해주세요.
    public Reservation createReservation(Long doctorId, Long patientId, LocalTime reservationTime) {
        LocalDateTime fullDateTime = LocalDateTime.of(LocalDate.now(), reservationTime);

        if (reservationTime.isBefore(LocalTime.of(9, 0)) || reservationTime.isAfter(LocalTime.of(16,0))) {
            throw new IllegalArgumentException("의사의 진료 가능 시간(09:00~17:00)내에서만 예약할 수 있습니다.");
        }
        if (reservationRepository.findByReservationTime(fullDateTime).isPresent()) {
            throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
        }
        Reservation reservation = Reservation.of(doctorId, patientId, fullDateTime);
        return reservationRepository.save(reservation);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id, String reason) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()) {
            System.out.println("예약 ID: " + id + " 취소 사유: " + reason);
            throw new NoSuchElementException("존재하지 않는 예약입니다.");
        }
        reservationRepository.deleteById(id);
    }
}