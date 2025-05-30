package com.scaler.bookmymovie.repositories;

import com.scaler.bookmymovie.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository< Theatre, Long> {
    Theatre findByAddress(String address);
}
