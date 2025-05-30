package com.scaler.bookmymovie.exceptions;

public class MovieAlreadyExist extends RuntimeException{

public MovieAlreadyExist(){
    super("Movie Already Exist!");
}
}
