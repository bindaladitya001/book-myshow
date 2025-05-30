package com.scaler.bookmymovie.controllers;

import com.scaler.bookmymovie.dtos.TicketDto;
import com.scaler.bookmymovie.repositories.TicketRepository;
import com.scaler.bookmymovie.response.TicketResponse;
import com.scaler.bookmymovie.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;
    TicketController(TicketService ticketService){
        this.ticketService=ticketService;

    }

    @PostMapping("/bookTicket")
    public  ResponseEntity<TicketResponse> bookTicket(@RequestBody TicketDto ticketDto){

        TicketResponse response;
        try {
            response = ticketService.bookTicket(ticketDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<TicketResponse>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping (path="/{ticketID}")
    public  ResponseEntity<TicketResponse> getTicket(@PathVariable Long ticketID){

        TicketResponse response;
        try {
            response = ticketService.getTicket(ticketID);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<TicketResponse>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping (path="/{ticketID}")
    public  ResponseEntity<TicketResponse> cancelTicket(@PathVariable Long ticketID){

        TicketResponse response;
        try {
            response = ticketService.cancelTicket(ticketID);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<TicketResponse>(HttpStatus.BAD_REQUEST);
        }
    }
}
