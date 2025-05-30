package com.scaler.payment.paymentService.models;

import jakarta.persistence.*;

@MappedSuperclass
public class BasePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tid", nullable = false)
    private Long tid;
}
