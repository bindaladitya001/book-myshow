package com.scaler.payment.paymentService.paymentAdaptor;

import com.scaler.payment.paymentService.enums.PaymentStatus;

public class CreditCardPayment implements CardPayment{
    private String cardHolderName = null;
    private int cvv = 0;
    private Long cardNumber= 0L;
    private String expiryDate = null;
    public CreditCardPayment(String cardHolderName, int cvv, Long cardNumber, String expiryDate)
    {
    this.cardHolderName=cardHolderName;
    this.cvv=cvv;
    this.expiryDate=expiryDate;
    this.cardNumber=cardNumber;
    }

    @Override
    public boolean isBalanceAvailable() {
        //implement to check balance is there in account
        return true;
    }

    @Override
    public String makePayment() {
        //Need to check this with bank and update the status accordignly
        return String.valueOf(PaymentStatus.COMPLETED);
    }
}
