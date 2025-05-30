package com.scaler.bookmymovie.models;

import com.scaler.bookmymovie.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TheatreSeat extends  BaseModel {
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;
}
