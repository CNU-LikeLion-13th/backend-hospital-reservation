package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Long doctorId, Long patientId) {
        Reservation reservation = new Reservation();
        reservation.setDoctorId(doctorId);
        reservation.setPatientId(patientId);
        reservation.setReservationTime(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    // TODO : 예약을 취소하는 코드를 작성해주세요.
    public void cancelReservation(Long id) {
        return;
    }
}
