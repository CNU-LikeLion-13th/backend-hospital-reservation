package com.example.hospitalreservation.model;

import java.time.LocalTime;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;
    private final LocalTime CONSULTATION_START_TIME = LocalTime.of(9, 0);
    private final LocalTime CONSULTATION_END_TIME = LocalTime.of(17, 0);

    // 이 시간에 진료가 가능한가 라는 판단을 의사 객체에서 수행함
    public boolean isAvailableAt(LocalTime time) {
        return !time.isBefore(CONSULTATION_START_TIME) && time.isBefore(CONSULTATION_END_TIME);
    }
}