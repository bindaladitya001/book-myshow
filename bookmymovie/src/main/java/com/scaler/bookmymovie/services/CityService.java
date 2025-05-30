package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.dtos.CityDto;
import com.scaler.bookmymovie.models.City;
import com.scaler.bookmymovie.repositories.CityRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.type.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {


    private static final Logger LOGGER= LoggerFactory.getLogger(CityService.class);
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

public ResponseEntity addCity(CityDto cityDto){

    City city=new City();
    city.setName(cityDto.getName());
    cityRepository.save(city);
    return new ResponseEntity<>( HttpStatus.OK);

}

}
