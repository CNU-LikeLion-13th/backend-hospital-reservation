package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Doctor;
import org.springframework.stereotype.Service;
import com.example.hospitalreservation.repository.DoctorRepository;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepsotory){
        this.doctorRepository = doctorRepsotory;
    }

    public Doctor getDoctor() {
        return doctorRepository.getDoctor();
    }
}
