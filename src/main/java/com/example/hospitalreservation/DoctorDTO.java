package com.example.hospitalreservation;

import com.example.hospitalreservation.model.Doctor;

public class DoctorDTO {
    private final Long id;
    private final int startHour;
    private final int endHour;

    public DoctorDTO(Long id, int startHour, int endHour) {
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static DoctorDTO from(Doctor doctor) {
        return new DoctorDTO(
                doctor.id,
                doctor.startHour,
                doctor.endHour
        );
    }
}

