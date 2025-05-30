package com.scaler.bookmymovie.dtos;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class ShowSeatDto {
    private Long showId;
    private Integer priceOfGoldSeat;
    private Integer priceOfDiamondSeat;
    private Integer priceOfSilverSeat;
}
