package com.scaler.bookmymovie.exceptions;

public class UserAlreadyExist extends RuntimeException{

public UserAlreadyExist(){
    super("User Already Exist!");
}
}
