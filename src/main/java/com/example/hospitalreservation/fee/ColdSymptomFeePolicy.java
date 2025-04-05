package com.example.hospitalreservation.fee;

import org.springframework.stereotype.Component;

@Component
public class ColdSymptomFeePolicy implements FeePolicy {
    @Override
    public int calculateFee() {
        return 15000;
    }

    @Override
    public String getReason() {
        return "감기 증상";
    }
}
