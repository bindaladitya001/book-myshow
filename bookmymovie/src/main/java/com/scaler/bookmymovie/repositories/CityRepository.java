package com.scaler.bookmymovie.repositories;

import com.scaler.bookmymovie.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City,Long>{
}
