package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.ReservationRequest;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
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
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationService.createReservation(
                    request.getPatientId(),
                    request.getDoctorId(),
                    request.getReservationTime(),
                    request.getReason()
            );

            Long reservationId = reservation.getId();

            return ResponseEntity.ok().body(Map.of(
                    "reservationId", reservationId,
                    "message", "예약이 완료되었습니다."
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요."
            ));
        }
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}