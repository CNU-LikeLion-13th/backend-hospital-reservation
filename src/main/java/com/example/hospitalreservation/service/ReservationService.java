package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Doctor;
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

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        Reservation newReservation = Reservation.of(doctorId, patientId, reservationTime);
        return reservationRepository.save(newReservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
