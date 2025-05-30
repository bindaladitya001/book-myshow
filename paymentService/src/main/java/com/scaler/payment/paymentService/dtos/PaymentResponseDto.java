package com.scaler.payment.paymentService.dtos;

import lombok.Data;

@Data
public class PaymentResponseDto {
    private Long ticketId;
    private String createdOn;
    private String status;
    private Long transactionId;
}
