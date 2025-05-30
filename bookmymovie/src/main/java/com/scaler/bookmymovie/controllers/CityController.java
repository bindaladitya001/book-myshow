package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.CityDto;
import com.scaler.bookmymovie.services.CityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;

@RestController
@RequestMapping("/city")

public class CityController {

    private final CityService cityService;

    CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/addcity")
    public ResponseEntity addCity(@RequestBody CityDto city) {

        return  cityService.addCity(city);
    }

}





