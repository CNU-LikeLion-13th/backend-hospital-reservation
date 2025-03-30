package com.example.hospitalreservation.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Doctor {
    private Long id;
    private String name;
    private String specialization;
    private final int START_HOUR = 9;
    private final int END_HOUR = 17;

    public void isValid(LocalDateTime reservationTime) {
        isValidRange(reservationTime);
        isValidUnit(reservationTime);
    }

    private void isValidRange(LocalDateTime reservationTime) {
        if (reservationTime.getHour() < START_HOUR || reservationTime.getHour() > END_HOUR) {
            throw new IllegalArgumentException("의사의 진료 가능 시간 (09:00~17:00) 내에서만 예약할 수 있습니다.");
        }
    }

    private void isValidUnit(LocalDateTime reservationTime) {
        if (reservationTime.getMinute() != 0) {
            throw new IllegalArgumentException("예약 시간은 1시간 단위여야 합니다.");
        }
    }
}