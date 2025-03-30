package com.example.hospitalreservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ReservationRequest {
    private Long doctorId;
    private Long patientId;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime reservationTime;
}