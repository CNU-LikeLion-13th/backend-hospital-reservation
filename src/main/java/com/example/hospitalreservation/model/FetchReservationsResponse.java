package com.example.hospitalreservation.model;

import com.example.hospitalreservation.domain.Reservation;

import java.util.List;

public class FetchReservationsResponse {

    private List<FetchReservationResponse> reservations;


    private FetchReservationsResponse(List<FetchReservationResponse> reservations) {
        this.reservations = reservations;
    }

    public static FetchReservationsResponse from(List<Reservation> reservations) {
        List<FetchReservationResponse> fetchReservationResponse =
                reservations.stream()
                        .map(FetchReservationResponse::from)
                        .toList();

        return new FetchReservationsResponse(fetchReservationResponse);
    }

    public List<FetchReservationResponse> getReservations() {
        return reservations;
    }
}

