package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.stereotype.Component;

@Component
public class ReservationRegister {

    private final ReservationRepository reservationRepository;

    public ReservationRegister(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation register(Reservation reservation) {
        boolean isOverlapping = reservationRepository.findAll().stream()
                .anyMatch(existingReservation -> existingReservation.isOverlapping(existingReservation.getReservationTime(), reservation.getReservationTime()));
        if (isOverlapping) {
            throw new ApplicationException(ReservationExceptionCode.DUPLICATE_RESERVATION_TIME);
        }
        return reservationRepository.save(reservation);
    }
}
