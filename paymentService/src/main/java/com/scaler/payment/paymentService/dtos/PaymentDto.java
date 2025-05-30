package com.scaler.payment.paymentService.dtos;

import lombok.Data;

@Data
public class PaymentDto {
    private double amount;
    private Long ticketId;
    private String createdOn;
    private String status;
}
