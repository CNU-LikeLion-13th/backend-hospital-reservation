package com.example.hospitalreservation.reservation.service;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.domain.ReservationRegister;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.service.command.CreateReservationCommand;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRegister reservationRegister;
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRegister reservationRegister, ReservationRepository reservationRepository) {
        this.reservationRegister = reservationRegister;
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(CreateReservationCommand createReservationCommand) {
        Reservation reservation = createReservationCommand.toReservation();
        Reservation registeredReservation = reservationRegister.register(reservation);
        return registeredReservation;
    }

    public boolean cancelReservation(Long id) {
        return reservationRepository.deleteById(id);
    }
}
