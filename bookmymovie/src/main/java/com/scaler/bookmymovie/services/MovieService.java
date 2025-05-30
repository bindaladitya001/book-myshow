package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.dtos.MovieDto;
import com.scaler.bookmymovie.exceptions.MovieAlreadyExist;
import com.scaler.bookmymovie.models.Movie;
import com.scaler.bookmymovie.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository){
        this.movieRepository= movieRepository;
    }

    public String addMovie(MovieDto request){

        String response;
        List<Movie> movies;
        movies = movieRepository.findAll();
        for(Movie mv: movies)
        {
            if(mv.getMovieName().equals(request.getMovieName()))
            {
                throw new MovieAlreadyExist();
            }
        }

        Movie movie=new Movie();
        movie.setMovieName(request.getMovieName());
        movie.setDuration(request.getDuration());
        movie.setLanguage(request.getLanguage());
        movie.setReleaseDate(request.getReleaseDate());

        movie.setGenre(request.getGenre());

        movieRepository.save(movie);
        response="Movie Added Successfully";
        return response;

    }

    public List<MovieDto> getAllMovies()
    {
       List<Movie> movies=new ArrayList<>();
       movies=movieRepository.findAll();
       List<MovieDto> movieDtos=new ArrayList<>();

        for(Movie movie:movies)
        {
            MovieDto mdto= new MovieDto();
            mdto.setMovieName(movie.getMovieName());
            mdto.setDuration(movie.getDuration());
            mdto.setGenre(movie.getGenre());
            mdto.setLanguage(movie.getLanguage());
            mdto.setReleaseDate(movie.getReleaseDate());
            movieDtos.add(mdto);
        }
    return movieDtos;
    }


}
