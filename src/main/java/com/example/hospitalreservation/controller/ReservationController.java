package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
    // TODO : 필요한 어노테이션을 작성해주세요.
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);

        return "index.html";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // TODO : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public String createReservation(@RequestParam Long doctorId,
                                    @RequestParam Long patientId,
                                    @RequestParam String reservationTime,
                                    Model model) {
        try {
            LocalTime time = LocalTime.parse(reservationTime);
            reservationService.createReservation(doctorId, patientId, time);
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "reservation_form"; // 다시 예약 폼으로 이동
        }
    }


    @PostMapping("/delete/{id}")
    public String cancelReservationFromForm(@PathVariable Long id,
                                            @RequestParam String cancelReason,
                                            Model model) {
        try {
            reservationService.cancelReservation(id, cancelReason);
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "존재하지 않는 예약입니다.");
        }
        return "redirect:/reservations";
    }
}