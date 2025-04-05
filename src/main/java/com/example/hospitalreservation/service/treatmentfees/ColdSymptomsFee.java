package com.example.hospitalreservation.service.treatmentfees;

import com.example.hospitalreservation.domain.Treatments;
import org.springframework.stereotype.Component;


@Component
public class ColdSymptomsFee implements TreatmentsFee {
    private static final Integer BASE_FEE = 10000;

    @Override
    public Integer getFee() {
        return BASE_FEE;
    }

    @Override
    public Treatments getTreatment() {
        return Treatments.COLD_SYMPTOMS;
    }
}
