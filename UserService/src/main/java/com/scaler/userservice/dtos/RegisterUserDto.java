package com.scaler.userservice.dtos;


import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;


}
