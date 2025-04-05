package com.example.hospitalreservation.service.treatmentfees;

import com.example.hospitalreservation.enums.Treatments;
import org.springframework.stereotype.Component;

@Component
public class FatigueRecoveryInjectionFee implements TreatmentsFee {

    private static final Integer BASE_FEE = 15000;

    @Override
    public Integer getFee() {
        return BASE_FEE;
    }

    @Override
    public Treatments getTreatment() {
        return Treatments.FATIGUE_RECOVERY_INJECTION;
    }
}
