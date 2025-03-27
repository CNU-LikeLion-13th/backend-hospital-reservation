package com.example.hospitalreservation.reservation.controller;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.controller.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.controller.request.DeleteReservationRequest;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.exception.ReservationExceptionCode;
import com.example.hospitalreservation.reservation.service.ReservationService;
import com.example.hospitalreservation.reservation.service.command.CreateReservationCommand;
import com.example.hospitalreservation.reservation.service.command.DeleteReservationCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String getReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "index";
    }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }

    @PostMapping()
    public String createReservation(CreateReservationRequest createReservationRequest, Model model) {
        try {
            CreateReservationCommand createReservationCommand = createReservationRequest.toCommand();
            reservationService.createReservation(createReservationCommand);
            return "redirect:/reservations";
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getCode().getMessage());
            return "reservation_form";
        }
    }

    @PostMapping("/delete/{id}")
    public String cancelReservation(@PathVariable Long id, @RequestParam String cancelReason, Model model) {
        DeleteReservationCommand deleteReservationCommand = DeleteReservationRequest.toCommand(id, cancelReason);
        if (!reservationService.cancelReservation(deleteReservationCommand)) {
            model.addAttribute("errorMessage", ReservationExceptionCode.RESERVATION_NOT_FOUND);
        }
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "redirect:/reservations";
    }
}
