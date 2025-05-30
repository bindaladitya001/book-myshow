package com.scaler.bookmymovie.services;

import com.scaler.bookmymovie.dtos.TicketDto;
import com.scaler.bookmymovie.exceptions.ShowDoesNotExists;
import com.scaler.bookmymovie.exceptions.TicketDoesNotExists;
import com.scaler.bookmymovie.exceptions.UserDoesNotExists;
import com.scaler.bookmymovie.models.Show;
import com.scaler.bookmymovie.models.ShowSeat;
import com.scaler.bookmymovie.models.Ticket;
import com.scaler.bookmymovie.models.User;
import com.scaler.bookmymovie.repositories.ShowRepository;
import com.scaler.bookmymovie.repositories.TicketRepository;
import com.scaler.bookmymovie.repositories.UserRepository;
import com.scaler.bookmymovie.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;


    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

   public TicketResponse bookTicket(TicketDto ticketDto){

       Optional<Show> showOptional=showRepository.findById(ticketDto.getShowId());

       if(showOptional.isEmpty())
       {
           throw new ShowDoesNotExists();
       }
       Optional<User> userOptional=userRepository.findById(ticketDto.getUserId());

       if(userOptional.isEmpty()){
           throw new UserDoesNotExists();
       }

       User user = userOptional.get();
       Show show = showOptional.get();

       Boolean isSeatAvailable = isSeatAvailable(show.getShowSeats(), ticketDto.getSeatsToBook());


       // count price
       Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeats(),	ticketDto.getSeatsToBook());

       String seats = listToString(ticketDto.getSeatsToBook());

       Ticket ticket = new Ticket();
       ticket.setTotalPrice(getPriceAndAssignSeats);
       ticket.setBookedSeat(seats);
       ticket.setUser(user);
       ticket.setShow(show);

       ticket = ticketRepository.save(ticket);

       user.getTicketList().add(ticket);
       show.getTickets().add(ticket);
       userRepository.save(user);
       showRepository.save(show);

       TicketResponse ticketResponse=new TicketResponse();
       ticketResponse.setBookedSeats(ticket.getBookedSeat());

       ticketResponse.setAddress(show.getTheatre().getAddress());
       ticketResponse.setTheaterName(show.getTheatre().getName());
       ticketResponse.setMovieName(show.getMovie().getMovieName());
       ticketResponse.setDate(show.getDate());
       ticketResponse.setTime(show.getStartTime());
       ticketResponse.setTotalPrice(ticket.getTotalPrice());

       return ticketResponse;

   }

    public TicketResponse getTicket(Long ticketId){

        Optional<Ticket> ticketOptional=ticketRepository.findById(ticketId);

        if(ticketOptional.isEmpty())
        {
            throw new TicketDoesNotExists();
        }

        Ticket ticket =new Ticket();
        ticket=ticketOptional.get();
        TicketResponse ticketResponse=new TicketResponse();
        ticketResponse.setBookedSeats(ticket.getBookedSeat());
        ticketResponse.setAddress(ticket.getShow().getTheatre().getAddress());
        ticketResponse.setTheaterName(ticket.getShow().getTheatre().getName());
        ticketResponse.setMovieName(ticket.getShow().getMovie().getMovieName());
        ticketResponse.setDate(ticket.getShow().getDate());
        ticketResponse.setTime(ticket.getShow().getStartTime());
        ticketResponse.setTotalPrice(ticket.getTotalPrice());
        return ticketResponse;

    }

    public TicketResponse cancelTicket(Long ticketId){

        Optional<Ticket> ticketOptional=ticketRepository.findById(ticketId);

        if(ticketOptional.isEmpty())
        {
            throw new TicketDoesNotExists();
        }

        Ticket ticket =new Ticket();
        ticket=ticketOptional.get();
        TicketResponse ticketResponse=new TicketResponse();
        //Set each seat to be Available
        //Set the tid to CANCELLED
        //Initiate the refund
        return ticketResponse;

    }



    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();

            if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
                return false;
            }
        }

        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        Integer totalAmount = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }

        return totalAmount;
    }

    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }


}
