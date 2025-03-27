package com.example.hospitalreservation.reservation.controller;

import com.example.hospitalreservation.common.exception.ApplicationException;
import com.example.hospitalreservation.reservation.controller.request.CreateReservationRequest;
import com.example.hospitalreservation.reservation.domain.Reservation;
import com.example.hospitalreservation.reservation.service.ReservationService;
import com.example.hospitalreservation.reservation.service.command.CreateReservationCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String cancelReservation(@PathVariable Long id) {
        boolean isCanceled = reservationService.cancelReservation(id);
        if (isCanceled) {
            return "redirect:/reservations";
        }

        return "nyah";
    }
}
