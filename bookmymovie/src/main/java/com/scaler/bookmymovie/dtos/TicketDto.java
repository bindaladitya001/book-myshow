package com.scaler.bookmymovie.dtos;

import com.scaler.bookmymovie.models.Show;
import com.scaler.bookmymovie.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
public class TicketDto {

    private Long showId;
    private Long userId;
    private List<String> seatsToBook;

}
