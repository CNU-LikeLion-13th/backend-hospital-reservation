package com.example.hospitalreservation.model;

public class Doctor {
    public Long id;
    public String name;
    public String specialization;

    public Doctor(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}