package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.domain.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationViewController {

    private final ReservationService reservationService;

    public ReservationViewController(ReservationService reservationService) {
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
}