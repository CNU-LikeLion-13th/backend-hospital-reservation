package com.example.hospitalreservation.service.treatmentfees;

import com.example.hospitalreservation.enums.Treatments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class TreatmentFeeStrategy {

    private final ConcurrentMap<Treatments, TreatmentsFee> calculateStrategyMap;


    // Collection Injection 사용
    // TreatmentsFeeCalculateStrategy를 구현한 모든 컴포넌트를 List Collection안에 넣는 DI 기법이라고 합니다.
    // 처음 써보게 되어 기록합니다.
    public TreatmentFeeStrategy(List<TreatmentsFee> treatmentsFees) {
        this.calculateStrategyMap = new ConcurrentHashMap<>();

        treatmentsFees.forEach(treatmentFee ->
                calculateStrategyMap.put(treatmentFee.getTreatment(), treatmentFee));
    }

    public TreatmentsFee getTreatmentFee(String reason) {
        return calculateStrategyMap.get(Treatments.from(reason));
    }
}
