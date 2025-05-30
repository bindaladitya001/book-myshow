package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.dtos.ShowDto;
import com.scaler.bookmymovie.dtos.ShowSeatDto;
import com.scaler.bookmymovie.enums.SeatType;
import com.scaler.bookmymovie.exceptions.MovieDoesNotExists;
import com.scaler.bookmymovie.exceptions.ShowDoesNotExists;
import com.scaler.bookmymovie.exceptions.TheatreDoesNotExists;
import com.scaler.bookmymovie.models.*;
import com.scaler.bookmymovie.repositories.MovieRepository;
import com.scaler.bookmymovie.repositories.ShowRepository;
import com.scaler.bookmymovie.repositories.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;



    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.movieRepository= movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public String addShow( ShowDto showDto) {

        Optional<Movie> movieOpt= movieRepository.findById(showDto.getMovieId());

        if( movieOpt.isEmpty()){
            throw new MovieDoesNotExists();
        }
        Optional<Theatre> theaterOpt =  theatreRepository.findById(showDto.getTheatreId());

        if (theaterOpt.isEmpty()) {
            throw new TheatreDoesNotExists();
        }
        Theatre theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        Show show = new Show();
        show.setStartTime(showDto.getStartTime());
        show.setDate(showDto.getDate());
        show.setName(showDto.getName());

        show.setMovie(movie);
        show.setTheatre(theater);
        show= showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theatreRepository.save(theater);

        return "Show has been added Successfully";
    }


    public String addSeatsInShow( ShowSeatDto showSeatDto) {

        Optional<Show> showOpt = showRepository.findById(showSeatDto.getShowId());

        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }

        Show show = showOpt.get();
        Theatre theater = show.getTheatre();

        List<TheatreSeat> theaterSeatList = theater.getTheaterSeats();

        List<ShowSeat> showSeatList = show.getShowSeats();

        for (TheatreSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if (showSeat.getSeatType().equals(SeatType.DIAMOND)) {
                showSeat.setPrice(showSeatDto.getPriceOfDiamondSeat());
            }
            else  if (showSeat.getSeatType().equals(SeatType.GOLD)) {
                showSeat.setPrice(showSeatDto.getPriceOfGoldSeat());
            }
            else {
                showSeat.setPrice(showSeatDto.getPriceOfSilverSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodBooked(Boolean.FALSE);

            showSeatList.add(showSeat);
        }

        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

}
