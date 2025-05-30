package com.scaler.bookmymovie.dtos;

import com.scaler.bookmymovie.models.Theatre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityDto {
    String name;
    List<String> theatre;

}
