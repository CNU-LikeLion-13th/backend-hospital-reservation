package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.ConsultationTime;
import com.example.hospitalreservation.model.Doctor;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<>();

    public DoctorRepository() {
        doctors.add(new Doctor(
                1L, "의사1", "미정",
                ConsultationTime.NINE_TO_FIVE.getStart(),
                ConsultationTime.NINE_TO_FIVE.getEnd()));
    }

    public Doctor findById(Long id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 의사를 찾을 수 없습니다."));
    }

    public List<Doctor> findAll() {
        return doctors;
    }
}
