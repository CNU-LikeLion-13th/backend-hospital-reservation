package com.example.hospitalreservation.fee;

import org.springframework.stereotype.Component;

@Component
public class InjectionFeePolicy implements FeePolicy {
    @Override
    public int calculateFee() {
        return 25000;
    }

    @Override
    public String getReason() {
        return "피로 회복 주사";
    }
}
