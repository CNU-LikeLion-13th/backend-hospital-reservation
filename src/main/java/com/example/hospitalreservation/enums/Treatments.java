package com.example.hospitalreservation.enums;

import java.util.Arrays;

public enum Treatments {

    GENERAL_CHECKUP("일반 검진"),
    COLD_SYMPTOMS("감기 증상"),
    FATIGUE_RECOVERY_INJECTION("피로 회복 주사")

    ;

    private final String reason;

    Treatments(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public static Treatments from(String reason){
            return Arrays.stream(Treatments.values())
                    .filter(treatments -> treatments.getReason().equals(reason))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 진료 목적이 존재하지 않습니다."));
    }
}
