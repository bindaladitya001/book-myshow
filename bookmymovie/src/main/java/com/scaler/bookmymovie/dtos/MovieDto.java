package com.scaler.bookmymovie.dtos;

import com.scaler.bookmymovie.enums.Genre;
import com.scaler.bookmymovie.enums.Language;
import com.scaler.bookmymovie.models.Show;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieDto {

    private String movieName;
    private Integer duration;

    private String releaseDate;

    private Genre genre;

    private Language language;


}
