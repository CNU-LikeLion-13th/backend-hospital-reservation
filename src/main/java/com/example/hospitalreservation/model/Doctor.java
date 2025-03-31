package com.example.hospitalreservation.model;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;

    public Doctor(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}