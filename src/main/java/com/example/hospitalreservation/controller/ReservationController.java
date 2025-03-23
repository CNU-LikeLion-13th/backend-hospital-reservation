package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

// TODO_w2 : 컨트롤러에 필요한 어노테이션을 작성해주세요.
@Controller
@RequestMapping("/reservations")
// TODO_w2 : 요청 경로는 templates를 참고하여 작성해주세요.
public class ReservationController {
    // TODO_2 : 주입 받아야 할 객체를 설정해주세요. (각 레이어끼리 서로 결합도를 낮게 하면서 ~하는게 MVC의 목적이다. DI라는 종속성을 직접 넣어준다?
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    //단순히 멤버변수를 두면 된다 Spring이 의존성을 알아서 해준다??

    // TODO_w2 : 필요한 어노테이션을 작성해주세요.
    @GetMapping
    public String getReservations(Model model) {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations/list";
    }

    // TODO_w2 : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // TODO_w2 : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservations/form";
    }

    // TODO_w2 : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public String createReservation(@RequestParam Long doctorId, @RequestParam Long patientId) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        reservationService.createReservation(doctorId, patientId, LocalDateTime.now());
        return "redirect:/reservations";
    }

    // TODO_w2 : 필요한 어노테이션을 작성해주세요.
    @DeleteMapping("/{id}")
    public String cancelReservation(@PathVariable Long id) {
        // TODO_w2 : 예약을 취소하는 코드를 작성해주세요.
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}