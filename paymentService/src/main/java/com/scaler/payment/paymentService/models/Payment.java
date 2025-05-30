package com.scaler.payment.paymentService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PAYMENT")
public class Payment extends BasePayment{
private double amount;
private Long ticketId;
private String createdOn;
private String status;

}
