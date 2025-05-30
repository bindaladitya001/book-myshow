package com.scaler.bookmymovie.repositories;

import com.scaler.bookmymovie.dtos.MovieDto;
import com.scaler.bookmymovie.models.City;
import com.scaler.bookmymovie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {



}
