package com.example.hospitalreservation.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;

    // 의사 진료 시간
    private final LocalTime consultationStartTime;
    private final LocalTime consultationEndTime;

    // 생성자
    public Doctor(Long id, String name, String specialization,
                  LocalTime consultationStartTime, LocalTime consultationEndTime) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.consultationStartTime = consultationStartTime;
        this.consultationEndTime = consultationEndTime;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getConsultationStartTime() {
        return consultationStartTime;
    }

    public LocalTime getConsultationEndTime() {
        return consultationEndTime;
    }

    // 이 시간에 진료가 가능한가 라는 판단을 의사 객체에서 수행함
    public boolean isWithinConsultationTime(LocalTime time) {
        return !time.isBefore(consultationStartTime) && time.isBefore(consultationEndTime);
    }
}