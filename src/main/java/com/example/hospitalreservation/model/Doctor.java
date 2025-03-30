package com.example.hospitalreservation.model;

import java.time.LocalTime;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;
    private LocalTime availableStartTime = LocalTime.of(9, 0);
    private LocalTime availableEndTime = LocalTime.of(17, 0);
    public Doctor() {}

    public LocalTime getAvailableStartTime() {
        return availableStartTime;
    }

    public LocalTime getAvailableEndTime() {
        return availableEndTime;
    }
}