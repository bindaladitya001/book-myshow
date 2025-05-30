package com.scaler.bookmymovie.repositories;

import com.scaler.bookmymovie.models.Theatre;
import com.scaler.bookmymovie.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
