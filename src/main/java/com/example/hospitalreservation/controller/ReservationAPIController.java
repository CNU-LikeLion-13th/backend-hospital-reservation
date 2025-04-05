package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.*;
import com.example.hospitalreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
public class ReservationAPIController {

    private final ReservationService reservationService;
    public ReservationAPIController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 리스트 조회
    @GetMapping("")
    public ResponseEntity<List<ShowReservationResponse>> getReservations() {
        return ResponseEntity.ok().body(reservationService.getAllReservations());
    }

    // 예약 등록
    @PostMapping("")
    public ResponseEntity<CreateReservationResponse> reservation(@RequestBody CreateReservationRequest dto) {
        CreateReservationResponse response;
        try {
            response = reservationService.createReservation(dto);
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e) {
            response = CreateReservationResponse.failure("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 예약 취소
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationResponse> cancelReservation(@RequestBody DeleteReservationRequest dto, @PathVariable Long id) {
        DeleteReservationResponse response;
        try {
            response = reservationService.cancelReservation(dto, id);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response = DeleteReservationResponse.failure(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
