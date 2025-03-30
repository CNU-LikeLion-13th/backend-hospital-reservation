package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
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
        return "index.html";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form.html";
    }

    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId,
                                    @RequestParam LocalDateTime reservationTime) {
        reservationService.createReservation(doctorId, patientId, reservationTime);
        return "redirect:/reservations";
    }

    @PostMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id, @RequestParam String cancellationReason) {
        log.info("예약 ID: {}가 취소되었습니다. 사유: {}", id, cancellationReason);
        reservationService.cancelReservation(id, cancellationReason);
        return "redirect:/reservations";
    }
}