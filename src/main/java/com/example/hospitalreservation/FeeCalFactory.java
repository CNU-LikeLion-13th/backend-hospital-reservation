package com.example.hospitalreservation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class FeeCalFactory {
    private final Map<String, FeeCal> feeMap = new HashMap<>();

    public FeeCalFactory() {
        feeMap.put("일반 검진", new NomalFee());
        feeMap.put("감기 증상", new ColdFee());
        feeMap.put("피로 회복 주사", new FatigueFee());
    }

    public int calculateFee(String reason) {
        FeeCal cal = feeMap.getOrDefault(reason, () -> 5000); // 기본 5000원
        return cal.calFee();
    }
}
