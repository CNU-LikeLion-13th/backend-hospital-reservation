package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Controller
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
    private final ReservationService reservationService;

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping()
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "index";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // TODO : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping()
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        try {
            reservationService.createReservation(doctorId, patientId, LocalDateTime.now());
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            return "nyah";
        }
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}