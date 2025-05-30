package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.MovieDto;
import com.scaler.bookmymovie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {


    private MovieService movieService;

    MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto request){
        try {
            String response;
            response = movieService.addMovie(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/movies")
    public List<MovieDto> getAllMovies()
    {
       return movieService.getAllMovies();
    }

}
