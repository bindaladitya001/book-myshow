package com.scaler.payment.paymentService.paymentAdaptor;

public interface CardPayment
{
    String cardHolderName = null;
    int cvv = 0;
    Long cardNumber= 0L;
    String expiryDate = null;

    public boolean isBalanceAvailable();

    public String makePayment();
}
