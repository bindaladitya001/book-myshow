package com.scaler.bookmymovie.response;

import lombok.Data;

@Data
public class TicketResponse {
    private String time;
    private String date;
    private String movieName;
    private String theaterName;
    private String address;
    private String bookedSeats;
    private double totalPrice;
}
