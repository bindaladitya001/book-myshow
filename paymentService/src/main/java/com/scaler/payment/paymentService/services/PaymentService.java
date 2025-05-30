package com.scaler.payment.paymentService.services;

import com.scaler.payment.paymentService.dtos.CreditCardDto;
import com.scaler.payment.paymentService.dtos.PaymentDto;
import com.scaler.payment.paymentService.dtos.PaymentResponseDto;
import com.scaler.payment.paymentService.dtos.TicketResponse;
import com.scaler.payment.paymentService.enums.PaymentStatus;
import com.scaler.payment.paymentService.models.Payment;
import com.scaler.payment.paymentService.paymentAdaptor.CardPayment;
import com.scaler.payment.paymentService.paymentAdaptor.CreditCardPayment;
import com.scaler.payment.paymentService.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;

    PaymentService(PaymentRepository movieRepository){
        this.paymentRepository= movieRepository;
    }

    public PaymentResponseDto completePayment(Long ticket_id, CreditCardDto cardDto) {
        String paymentStatus = "";
        //Get the totalAmount from the bookmymovie service
        Payment paymentDto=new Payment();

        paymentDto.setAmount(getTicketPrice(ticket_id));

        CardPayment cardPayment=new CreditCardPayment(cardDto.getCardHolder(), cardDto.getCvv(),cardDto.getCardNumber(),cardDto.getExpiryDate());

        if(cardPayment.isBalanceAvailable()){
            paymentDto.setStatus(cardPayment.makePayment());
        }
        else{
            paymentStatus= String.valueOf(PaymentStatus.DECLINED);
            paymentDto.setStatus(paymentStatus);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        paymentDto.setCreatedOn(formatter.format(date));

        paymentDto.setTicketId(ticket_id);
        paymentRepository.save(paymentDto);
        PaymentResponseDto paymentResponseDto=new PaymentResponseDto();
        paymentResponseDto.setStatus(paymentDto.getStatus());
        paymentResponseDto.setTransactionId(paymentDto.getTicketId());
        paymentResponseDto.setTicketId(paymentDto.getTicketId());
        paymentResponseDto.setCreatedOn(paymentDto.getCreatedOn());

    return paymentResponseDto;
    }

    private double getTicketPrice(Long id) {
        TicketResponse ticketResponse = restTemplate.getForObject("http://localhost:8081/ticket/{id}", TicketResponse.class, id);
        return ticketResponse.getTotalPrice();
    }

}
