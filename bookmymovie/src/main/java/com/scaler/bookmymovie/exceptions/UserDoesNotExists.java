package com.scaler.bookmymovie.exceptions;

public class UserDoesNotExists extends RuntimeException {


    public UserDoesNotExists() {
        super("User dose not Exists");
    }
}
