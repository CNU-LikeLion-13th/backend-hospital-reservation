package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Doctor;

public class DoctorDTO {
    public final Long id;
    public final String name;
    public final String specialization;

    public DoctorDTO(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public static DoctorDTO from(Doctor doctor) {
        return new DoctorDTO(
                doctor.id,
                doctor.name,
                doctor.specialization
        );
    }
}

