package com.example.hospitalreservation.reservation.domain;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class ReservationRegister {

    private final ReservationRepository reservationRepository;

    public ReservationRegister(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation register(Reservation reservation) {
        if (reservationRepository.existsByReservationTime(reservation.getReservationTime())) {
            throw new ApplicationException(ReservationExceptionCode.DUPLICATE_RESERVATION_TIME);
        }
        return reservationRepository.save(reservation);
    }
}
