package com.scaler.bookmymovie.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "SHOWS")
public class Show  extends BaseModel {
    private String name;

    private String startTime;
    private String date;

    @ManyToOne
    private Movie movie;

   @ManyToOne
   @JoinColumn
    private Theatre theatre;

   @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
   private List<ShowSeat> showSeats =new ArrayList<>();

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> tickets =new ArrayList<>();




}
