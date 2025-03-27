package com.example.hospitalreservation.reservation.domain.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservationCanceler {

    private final ReservationRepository reservationRepository;

    public ReservationCanceler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean cancel(DeleteReservationCommand deleteReservationCommand) {
        try {
            Reservation toDelete = reservationRepository.findById(deleteReservationCommand.id());
            log.info("예약 ID {} 취소됨. 사유: {}", deleteReservationCommand.id(), deleteReservationCommand.cancelReason());
            return reservationRepository.deleteById(toDelete.getId());
        } catch (ApplicationException e) {
            log.warn("예약 취소 실패 - ID {}: {}", deleteReservationCommand.id(), ReservationExceptionCode.RESERVATION_NOT_FOUND.getMessage());
            return false;
        }
    }
}
