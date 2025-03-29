package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final Doctor doctor;
    private Long nextId = 1L;

    public ReservationService(ReservationRepository reservationRepository, Doctor doctor) {
        this.reservationRepository = reservationRepository;
        this.doctor = doctor;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationDTO::fromReservation)
                .collect(Collectors.toList());
    }

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime, String reason) {
        try {
            doctor.isValid(reservationTime); // 의사 진료 시간 범위 체크
            isExist(reservationTime); // 예약 시간 중복 체크
            Reservation reservation = Reservation.of(nextId++, doctorId, patientId, reservationTime, reason);
            return reservationRepository.save(reservation);
        } catch (Exception e){
            //
        }
        return null;
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
        return;
    }

    private void isExist(LocalDateTime reservationTime) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationTime().isEqual(reservationTime)) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        }
    }
}
