package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.CreateReservationRequest;
import com.example.hospitalreservation.model.DeleteReservationRequest;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public String createReservation(@ModelAttribute CreateReservationRequest dto) {
        Reservation reservation = reservationService.createReservation(dto);
        return "redirect:/reservations?reservationId="+reservation.getId();
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id,
                                    @ModelAttribute DeleteReservationRequest request) {
        reservationService.cancelReservation(id, request);
        return "redirect:/reservations";
    }
}