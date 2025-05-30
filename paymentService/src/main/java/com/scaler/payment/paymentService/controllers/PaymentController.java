package com.scaler.payment.paymentService.controllers;

import com.scaler.payment.paymentService.dtos.CreditCardDto;
import com.scaler.payment.paymentService.dtos.PaymentResponseDto;
import com.scaler.payment.paymentService.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    PaymentController(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/{ticket_id}")
    public PaymentResponseDto completePayment(@PathVariable Long ticket_id, @RequestBody CreditCardDto card_details)
    {
        PaymentResponseDto response = null;
        try {
            response = paymentService.completePayment(ticket_id,card_details);
            return response;
        }catch(Exception e)
        {
            response.setStatus(String.valueOf(new ResponseEntity<>(response, HttpStatus.CREATED)));
            return response;
        }
    }


}