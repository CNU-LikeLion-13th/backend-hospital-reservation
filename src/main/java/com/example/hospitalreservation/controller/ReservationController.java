package com.example.hospitalreservation.controller;

import java.time.LocalDateTime;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hospitalreservation.service.ReservationService;

// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.
@Controller
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
	private ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

    // TODO : 필요한 어노테이션을 작성해주세요.
	@GetMapping("/reservations")
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
		model.addAttribute("reservations", reservationService.getAllReservations());
		
        return "index";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
	@GetMapping("/reservations/new")
    public String showReservationForm() {
        // TODO : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
	@PostMapping("/reservations")
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDateTime reservationTime, Model model) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.

        try {
            Reservation reservation = reservationService.createReservation(doctorId, patientId, reservationTime);

            model.addAttribute("message", "예약이 완료되었습니다." + reservation.getId());
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "reservation_form";
        }
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
	@PostMapping("/reservations/delete/{id}")
    public String cancelReservation(@PathVariable Long id, @RequestParam String cancelReason, Model model) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.

        reservationService.cancelReservation(id, cancelReason);

        return "redirect:/reservations";
    }
}