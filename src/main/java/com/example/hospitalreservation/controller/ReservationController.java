package com.example.hospitalreservation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @GetMapping
    public String getReservations() { return "index"; }

    @GetMapping("/new")
    public String showReservationForm() {
        return "reservation_form";
    }
}