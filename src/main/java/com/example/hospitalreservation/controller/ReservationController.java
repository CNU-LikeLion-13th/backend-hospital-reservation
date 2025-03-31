package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.ReservationDto;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        List<ReservationDto> reservations = reservationService.getAllReservations();
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
    @PostMapping
    public String createReservation(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationTime,
            RedirectAttributes redirectAttributes,
            Model model) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        try {
            Reservation reservation = reservationService.createReservation(doctorId, patientId, reservationTime);
            redirectAttributes.addFlashAttribute("successMessage", "예약이 완료되었습니다. (예약 ID: " + reservation.getId() + ")");
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "reservation_form"; // 다시 그 페이지 보여줌
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