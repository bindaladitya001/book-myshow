package com.scaler.bookmymovie.dtos;

import com.scaler.bookmymovie.enums.Gender;
import com.scaler.bookmymovie.models.Ticket;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
    private String password;
}
