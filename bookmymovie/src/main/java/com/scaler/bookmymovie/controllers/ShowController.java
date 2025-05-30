package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.CityDto;
import com.scaler.bookmymovie.dtos.ShowDto;
import com.scaler.bookmymovie.dtos.ShowSeatDto;
import com.scaler.bookmymovie.services.CityService;
import com.scaler.bookmymovie.services.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")

public class ShowController {

    private final ShowService showService;

    ShowController(ShowService showService) {
        this.showService = showService;
    }


    @PostMapping("/addshow")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto) {

        try {
            String response;
            response = showService.addShow(showDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/addSeats")
    public ResponseEntity<String> addShow(@RequestBody ShowSeatDto showSeatDto) {

        try {
            String response;
            response = showService.addSeatsInShow(showSeatDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}





