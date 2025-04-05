package com.example.hospitalreservation.service.treatmentfees;

import com.example.hospitalreservation.enums.Treatments;
import org.springframework.stereotype.Component;


@Component
public class GeneralCheckupFee implements TreatmentsFee {
    private static final Integer BASE_FEE = 20000;

    @Override
    public Integer getFee() {
        return BASE_FEE;
    }

    @Override
    public Treatments getTreatment() {
        return Treatments.GENERAL_CHECKUP;
    }
}
