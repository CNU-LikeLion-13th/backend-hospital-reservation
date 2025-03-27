package com.example.hospitalreservation.reservation.application;

import com.example.hospitalreservation.reservation.domain.entity.Reservation;
import com.example.hospitalreservation.reservation.domain.service.ReservationCanceler;
import com.example.hospitalreservation.reservation.domain.service.ReservationRegister;
import com.example.hospitalreservation.reservation.domain.repository.ReservationRepository;
import com.example.hospitalreservation.reservation.application.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.application.command.DeleteReservationCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationCanceler reservationCanceler;
    private final ReservationRegister reservationRegister;
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationCanceler reservationCanceler, ReservationRegister reservationRegister, ReservationRepository reservationRepository) {
        this.reservationCanceler = reservationCanceler;
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

    public boolean cancelReservation(DeleteReservationCommand deleteReservationCommand) {
        return reservationCanceler.cancel(deleteReservationCommand);
    }
}
