package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Patient;

public class PatientDTO {
    private final Long id;
    private final String name;
    private final int age;

    public PatientDTO(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static PatientDTO from(Patient patient) {
        return new PatientDTO(
                patient.id,
                patient.name,
                patient.age
        );
    }
}
