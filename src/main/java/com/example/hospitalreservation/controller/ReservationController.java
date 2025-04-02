package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.RequestDTO;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {


    // TODO : 주입 받아야 할 객체를 설정해주세요. (종속성을 직접 넣어준다...?)
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Map<String, Object> addReservation(@RequestBody RequestDTO request) {
        Map<String, Object> result = new HashMap<>();
        try{
            Reservation reservation = reservationService.createReservation(
                    request.doctorId,
                    request.patientId,
                    request.reservationStartTime
            );

        }
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "index";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // TODO : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        reservationService.createReservation(doctorId, patientId, LocalDateTime.now());
        return "redirect:/reservations";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id, String reason) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        reservationService.cancelReservation(id, reason);
        return "redirect:/reservations";
    }
}