package com.example.hospitalreservation.enums;

public enum ReservationType {

    GENERAL_CHECKUP("일반 검진", 10000L),
    COLD_SYMPTOM("감기 증상", 15000L),
    ENERGY_SHOT("피로 회복 주사", 25000L),
    UNKNOWN("기타", 0L);

    private final String purpose;
    private final Long fee;

    public String getPurpose() { return purpose; }
    public Long getFee() { return fee; }

    ReservationType(String purpose, Long fee) {
        this.purpose = purpose;
        this.fee = fee;
    }

    public static Long calculateFee(String reason) {
        for (ReservationType reservationType : values()) {
            if (reason.contains(reservationType.purpose)) {
                return reservationType.getFee();
            }
        }
        return 0L;
    }
}