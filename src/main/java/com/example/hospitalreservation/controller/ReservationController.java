package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.domain.Reservation;
import com.example.hospitalreservation.model.*;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);

        return "index.html";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form.html";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CreateReservationResponse> createReservation(@ModelAttribute CreateReservationRequest dto) {
        CreateReservationResponse response = reservationService.createReservation(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<DeleteReservationResponse> cancelReservation
            (@PathVariable Long id, @ModelAttribute DeleteReservationRequest request) {
        DeleteReservationResponse response = reservationService.cancelReservation(id, request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
    }
}