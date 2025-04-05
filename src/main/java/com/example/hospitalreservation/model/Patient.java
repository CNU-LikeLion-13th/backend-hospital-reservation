package com.example.hospitalreservation.model;

public class Patient {
    private Long id;
    private String name;
    private int age;

    // Getter 추가
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    // Setter 추가
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
//