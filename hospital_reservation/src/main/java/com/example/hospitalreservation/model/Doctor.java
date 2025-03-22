package com.example.hospitalreservation.model;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;

    // Getter 추가
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSpecialization() {
        return specialization;
    }
    // Setter 추가 (필요하면)
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
