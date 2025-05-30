package com.scaler.bookmymovie.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Theatre  extends BaseModel {
    private String name;
    private String address;
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<TheatreSeat> theaterSeats = new ArrayList<>();

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}
