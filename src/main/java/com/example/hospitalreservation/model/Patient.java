package com.example.hospitalreservation.model;

public class Patient {
    public Long id;
    public String name;
    public int age;

    public Patient(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
