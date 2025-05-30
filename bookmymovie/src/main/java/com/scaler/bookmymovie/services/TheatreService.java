package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.dtos.TheatreDto;
import com.scaler.bookmymovie.dtos.TheatreSeatDto;
import com.scaler.bookmymovie.enums.SeatType;
import com.scaler.bookmymovie.exceptions.TheatreAlreadyExist;
import com.scaler.bookmymovie.exceptions.TheatreDoesNotExists;
import com.scaler.bookmymovie.models.Theatre;
import com.scaler.bookmymovie.models.TheatreSeat;
import com.scaler.bookmymovie.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public String addTheatre(TheatreDto theatreDto ) {

        if(theatreRepository.findByAddress(theatreDto.getAddress()) !=null)
        {
            throw  new TheatreAlreadyExist();
        }
        Theatre theatre=new Theatre();
        theatre.setAddress(theatreDto.getAddress());
        theatre.setName(theatreDto.getName());
        theatreRepository.save(theatre);

        return "Theater has been saved Successfully";

    }

    public String addTheatreSeats(TheatreSeatDto seatDto ) {

        if (theatreRepository.findByAddress(seatDto.getAddress()) == null) {
            throw new TheatreDoesNotExists();
        }

        Long noOfSeatsInRow = seatDto.getNoOfSeatInRow();
        Long noOfDiamondSeat = seatDto.getNoOfDiamondSeat();
        Long noOfGoldSeats = seatDto.getNoOfGoldSeat();
        Long noOfSilverSeats = seatDto.getNoOfSilverSeat();
        String address = seatDto.getAddress();

        Theatre theatre = theatreRepository.findByAddress(address);

        List<TheatreSeat> seatList = theatre.getTheaterSeats();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for (int i = 1; i <= noOfDiamondSeat; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheatreSeat theaterSeat = new TheatreSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.DIAMOND);
            theaterSeat.setTheatre(theatre);
            seatList.add(theaterSeat);
        }

        for (int i = 1; i <= noOfGoldSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }
            TheatreSeat theaterSeat = new TheatreSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.GOLD);
            theaterSeat.setTheatre(theatre);
            seatList.add(theaterSeat);
        }

        for (int i = 1; i <= noOfSilverSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }
            TheatreSeat theaterSeat = new TheatreSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.SILVER);
            theaterSeat.setTheatre(theatre);
            seatList.add(theaterSeat);
        }

        theatreRepository.save(theatre);

        return "Theater Seats have been added successfully";

    }
}
