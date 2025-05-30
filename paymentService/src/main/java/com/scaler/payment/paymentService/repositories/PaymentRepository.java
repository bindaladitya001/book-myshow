package com.scaler.payment.paymentService.repositories;


import com.scaler.payment.paymentService.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {



}
