package com.scaler.bookmymovie.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "CITY")
public class City  extends BaseModel {
    private String name;
    private String state;
    private String zipcode;
    @OneToMany
    List<Theatre> theatre;

}
