package com.example.hospitalreservation.model;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;
    private int startHour;
    private int endHour;

    public Doctor(Long id, String name, String specialization, int startHour, int endHour) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public Long getId() {
        return id;
    }
}