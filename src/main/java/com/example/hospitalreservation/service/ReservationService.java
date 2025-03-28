package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationDTO::fromReservation)
                .collect(Collectors.toList());
    }

    public Reservation createReservation(Long doctorId, Long patientId) {
        LocalDateTime reservationTime = LocalDateTime.now();
        Reservation reservation = Reservation.of(doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
        return;
    }
}
