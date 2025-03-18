package com.example.hospitalreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        // TODO : 예약 목록을 가져오는 코드 작성
        return "index.html";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/reservation_form")
    public String showReservationForm() {
        return "reservation_form.html";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        return "index.html";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    public String cancelReservation(@PathVariable Long id) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        return "index.html";
    }
}