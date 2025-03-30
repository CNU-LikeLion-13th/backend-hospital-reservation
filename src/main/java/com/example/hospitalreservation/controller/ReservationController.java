package com.example.hospitalreservation.controller;

import java.time.LocalDateTime;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hospitalreservation.service.ReservationService;


@Controller
public class ReservationController {

	private ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
    public String getReservations(Model model) {
		model.addAttribute("reservations", reservationService.getAllReservations());
		
        return "index";
    }

	@GetMapping("/reservations/new")
    public String showReservationForm() {
        return "reservation_form";
    }

	@PostMapping("/reservations")
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDateTime reservationTime, Model model) {
        try {
            Reservation reservation = reservationService.createReservation(doctorId, patientId, reservationTime);

            model.addAttribute("message", "예약이 완료되었습니다." + reservation.getId());
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "reservation_form";
        }
    }

	@PostMapping("/reservations/delete/{id}")
    public String cancelReservation(@PathVariable Long id, @RequestParam String cancelReason, Model model) {
        reservationService.cancelReservation(id, cancelReason);

        return "redirect:/reservations";
    }
}