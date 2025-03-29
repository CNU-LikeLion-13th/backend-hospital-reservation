package com.example.hospitalreservation.model;

public class Doctor {
    public final Long id;
    public final int startHour;
    public final int endHour;

    public Doctor(Long id, int startHour, int endHour) {
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}