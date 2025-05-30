package com.scaler.bookmymovie.models;

import com.scaler.bookmymovie.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SHOW_SEATS")
public class ShowSeat  extends BaseModel {

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;

    private Boolean isAvailable;
    private Boolean isFoodBooked;

    @ManyToOne
    @JoinColumn
    private Show show;


}
