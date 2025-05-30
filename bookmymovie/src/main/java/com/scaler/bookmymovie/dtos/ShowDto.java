package com.scaler.bookmymovie.dtos;

import com.scaler.bookmymovie.models.Movie;
import com.scaler.bookmymovie.models.ShowSeat;
import com.scaler.bookmymovie.models.Theatre;
import com.scaler.bookmymovie.models.Ticket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShowDto {


    private String name;

    private String startTime;
    private String date;

    private Long movieId;

    private Long theatreId;


}
