package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ReservationRegister {

    private final ReservationRepository reservationRepository;

    public ReservationRegister(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation register(Reservation reservation) {
        validateReservation(reservation);
        return reservationRepository.save(reservation);
    }

    private void validateReservation(Reservation reservation) {
        validateReservationTime(reservation.getReservationTime());
    }

    private void validateReservationTime(LocalDateTime reservationTime) {
        if (!isWithinBusinessHours(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.OUT_OF_BUSINESS_HOURS);
        }

        if (!isHourlySlot(reservationTime.toLocalTime())) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }

        if (reservationRepository.existsByReservationTime(reservationTime)) {
            throw new ApplicationException(ReservationExceptionCode.DUPLICATE_RESERVATION_TIME);
        }

        boolean overlaps = reservationRepository.findAll().stream()
                .anyMatch(existingReservation -> isOverlapping(existingReservation.getReservationTime(), reservationTime));

        if (overlaps) {
            throw new ApplicationException(ReservationExceptionCode.INVALID_RESERVATION_TIME_RANGE);
        }
    }

    private boolean isWithinBusinessHours(LocalTime time) {
        return !time.isBefore(LocalTime.of(9, 0)) && !time.isAfter(LocalTime.of(16, 59));
    }

    private boolean isHourlySlot(LocalTime time) {
        return time.getMinute() == 0;
    }

    private boolean isOverlapping(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime startTime = existingTime;
        LocalDateTime endTime = startTime.plusHours(1);
        return !newTime.isBefore(startTime) && newTime.isBefore(endTime);
    }
}
