package com.scaler.bookmymovie.models;

import com.scaler.bookmymovie.enums.Genre;
import com.scaler.bookmymovie.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "MOVIES")
public class Movie extends BaseModel{

    @Column(nullable = false)
    private String movieName;

    private Integer duration;

    private String releaseDate;


    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows=new ArrayList<>();

}
