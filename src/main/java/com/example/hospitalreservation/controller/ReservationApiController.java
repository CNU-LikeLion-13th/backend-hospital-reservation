package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.CreateReservationRequest;
import com.example.hospitalreservation.model.CreateReservationResponse;
import com.example.hospitalreservation.model.DeleteReservationRequest;
import com.example.hospitalreservation.model.DeleteReservationResponse;
import com.example.hospitalreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationApiController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@Validated @RequestBody CreateReservationRequest request) {
        Long doctorId = request.getDoctorId();
        Long patientId = request.getPatientId();
        LocalDateTime reservationTime = request.getReservationTime();
        CreateReservationResponse response = reservationService.createReservation(doctorId, patientId, reservationTime);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReservationResponse> cancelReservation(@PathVariable Long id,
                                                                       @RequestBody DeleteReservationRequest request) {
        // 취소 사유를 서비스에 전달하여 로그 기록 및 처리
        DeleteReservationResponse response = reservationService.cancelReservation(id, request.getCancelReason());
        return ResponseEntity.ok(response);
    }
}