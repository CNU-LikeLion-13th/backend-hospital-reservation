package com.example.hospitalreservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteReservationResponse {
    private String message;
    private String error;

    public String getMessage() { return message;}
    public String getError() { return error;}

    private DeleteReservationResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public static DeleteReservationResponse success(String message) {
        return new DeleteReservationResponse(message, null);
    }

    public static DeleteReservationResponse failure(String error) {
        return new DeleteReservationResponse(null, error);
    }
}
