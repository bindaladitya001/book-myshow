package com.scaler.bookmymovie.models;

import com.scaler.bookmymovie.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class User extends BaseModel{
    @Column(nullable = false)
    private String name;
    private Integer age;
    private String adress;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String mobileNo;

    @Column(unique = true)
    private String emailId;
    private String roles;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> ticketList=new ArrayList<>();


}
