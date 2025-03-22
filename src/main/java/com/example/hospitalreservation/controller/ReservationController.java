package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }

    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        return null;
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        return null;
    }
}