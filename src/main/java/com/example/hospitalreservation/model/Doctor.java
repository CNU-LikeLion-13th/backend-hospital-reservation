package com.example.hospitalreservation.model;

public class Doctor {
    private Long id;
    private String name;
    private String specialization;

    // Doctor 클래스를 사용하지 않기 때문에 임시로 static으로 startHour, endHour를 설정
    private static int startHour = 9;
    private static int endHour = 17;

    public static int getStartHour() { return startHour; }
    public static int getEndHour() { return endHour; }
}