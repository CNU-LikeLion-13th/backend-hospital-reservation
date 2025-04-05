package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.dto.ReservationRequest;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;

    public ReservationApiController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        var saved = reservationService.createReservation(
                request.doctorId(),
                request.patientId(),
                request.reservationStartTime(),
                request.reservationEndTime(),
                request.reason()
        );

        return ResponseEntity.ok().body(
                new ReservationResponse(
                        saved.toDto().id(),
                        "예약이 완료되었습니다.",
                        saved.toDto().fee()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().body("예약이 취소되었습니다.");
    }

    public record ReservationResponse(
            Long reservationId,
            String message,
            int calculatedFee
    ) {}
}
