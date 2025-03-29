package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Patient;

public class PatientDTO {
    public final Long id;
    public final String name;
    public final int age;

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
