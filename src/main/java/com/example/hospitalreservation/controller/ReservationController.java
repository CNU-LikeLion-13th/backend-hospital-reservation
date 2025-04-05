package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.common.ErrorResponse;
import com.example.hospitalreservation.domain.Reservation;
import com.example.hospitalreservation.model.*;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> fetchAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        FetchReservationsResponse response = FetchReservationsResponse.from(reservations);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@ModelAttribute CreateReservationRequest dto) {
        CreateReservationResponse response = reservationService.createReservation(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationResponse> cancelReservation
            (@PathVariable Long id, @ModelAttribute DeleteReservationRequest request) {
        DeleteReservationResponse response = reservationService.cancelReservation(id, request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
    }
}