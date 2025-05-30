package com.scaler.bookmymovie.dtos;

import lombok.Data;

@Data
public class TheatreSeatDto
{
    private String address;
    private Long noOfSeatInRow;
    private Long noOfDiamondSeat;
    private Long noOfSilverSeat;
    private Long noOfGoldSeat;
}
