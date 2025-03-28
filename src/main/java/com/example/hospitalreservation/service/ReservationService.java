package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.DeleteReservationRequest;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import com.example.hospitalreservation.utils.GlobalLogger;
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

        // TODO: 의사의 시간에 포함되는가?
        // TODO: 다른 예약과 시간이 겹치지 않는가?

        return reservationRepository.save(newReservation);
    }

    public void cancelReservation(Long id, DeleteReservationRequest dto) {
        reservationRepository.deleteById(id);
        GlobalLogger.log(dto.getCancelReason());
    }
}
