package com.example.hospitalreservation.model;

public class DeleteReservationRequest {
    private String cancelReason;

    public DeleteReservationRequest(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelReason() {
        return cancelReason;
    }
}
