package com.scaler.payment.paymentService.dtos;

import lombok.Data;

@Data
public class CreditCardDto {
    private Long cardNumber;
    private String cardHolder;
    private int cvv;
    private String expiryDate;

}
