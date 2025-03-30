package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public class DoctorRepository {
    private final List<Doctor> doctors = new ArrayList<>();

    public DoctorRepository() {
        doctors.add(new Doctor(1L, "가나다", "내과", 9, 17));
    }

    public Doctor findById(Long id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Doctor> findAll() {
        return doctors;
    }
}
