package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        isValid(reservationTime);
        Reservation reservation = Reservation.of(doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
        return;
    }

    private void isValid(LocalDateTime reservationTime) {
        if (reservationTime.getHour() < 9 || reservationTime.getHour() > 17) {
            throw new IllegalArgumentException("의사의 진료 가능 시간 (09:00~17:00) 내에서만 예약할 수 있습니다.");
        }
        if (reservationTime.getMinute() != 0) {
            throw new IllegalArgumentException("예약 시간은 1시간 단위여야 합니다.");
        }
    }
}
