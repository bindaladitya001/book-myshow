package com.scaler.bookmymovie.exceptions;

public class TicketDoesNotExists extends RuntimeException {


    public TicketDoesNotExists() {
        super("Show dose not Exists");
    }
}
