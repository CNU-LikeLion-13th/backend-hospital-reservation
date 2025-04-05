package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.hospitalreservation.dto.ReservationDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public String createReservation(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationStartTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationEndTime,
            @RequestParam String reason
    ) {
        System.out.println("넘어온 시작 시간: " + reservationStartTime);
        System.out.println("넘어온 종료 시간: " + reservationEndTime);
        reservationService.createReservation(doctorId, patientId, reservationStartTime, reservationEndTime, reason);
        return "redirect:/reservations";
    }



    @GetMapping
    public String getReservations(Model model) {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        System.out.println("예약 취소 요청 들어옴! ID: " + id);
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}
