package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.ShowDto;
import com.scaler.bookmymovie.dtos.TheatreDto;
import com.scaler.bookmymovie.dtos.TheatreSeatDto;
import com.scaler.bookmymovie.services.ShowService;
import com.scaler.bookmymovie.services.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
    private final TheatreService theatreService;

    TheatreController(TheatreService theatreService) {
        this.theatreService= theatreService;
    }


    @PostMapping("/addTheatre")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreDto theatreDto ) {

        try {
            String response;
            response = theatreService.addTheatre(theatreDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addSeats")
    public ResponseEntity<String> addTheatreSeats(@RequestBody TheatreSeatDto theatreDto ) {

        try {
            String response;
            response = theatreService.addTheatreSeats(theatreDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
