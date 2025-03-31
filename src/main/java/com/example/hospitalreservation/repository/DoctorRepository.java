package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepository {
    private final Doctor doctor = new Doctor(1L, "Dr. Rian", "FM");

    public Doctor getDoctor(){
        return doctor;
    }
}