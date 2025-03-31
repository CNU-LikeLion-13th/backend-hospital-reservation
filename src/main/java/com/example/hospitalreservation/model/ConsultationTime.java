package com.example.hospitalreservation.model;

import java.time.LocalTime;

public enum ConsultationTime {
    NINE_TO_FIVE(LocalTime.of(9, 0), LocalTime.of(17, 0)),
    TEN_TO_SIX(LocalTime.of(10, 0), LocalTime.of(18, 0));

    private final LocalTime start;
    private final LocalTime end;

    ConsultationTime(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}

