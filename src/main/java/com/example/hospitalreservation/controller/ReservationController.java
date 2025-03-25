package com.example.hospitalreservation.controller;

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
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        reservationService.createReservation(doctorId, patientId, LocalDateTime.now());
        return "redirect:/reservations";
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}