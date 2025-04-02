package com.example.hospitalreservation.model;

public class Patient {
    public final Long id;
    public final String name;
    public final int age;

    public Patient(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
