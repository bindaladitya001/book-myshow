package com.scaler.bookmymovie.repositories;

import com.scaler.bookmymovie.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository< Show,Long> {
}
