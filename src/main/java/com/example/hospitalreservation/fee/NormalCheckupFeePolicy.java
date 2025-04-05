package com.example.hospitalreservation.fee;

import org.springframework.stereotype.Component;

@Component
public class NormalCheckupFeePolicy implements FeePolicy {
    @Override
    public int calculateFee() {
        return 10000;
    }

    @Override
    public String getReason() {
        return "일반 검진";
    }
}
