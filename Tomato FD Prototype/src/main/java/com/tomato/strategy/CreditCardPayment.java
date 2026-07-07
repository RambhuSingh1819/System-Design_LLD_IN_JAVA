package com.tomato.strategy;

public class CreditCardPayment implements IPaymentStrategy {
    private String cardNumber;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        String maskedCvv = cvv != null ? cvv.replaceAll(".", "*") : "***";
        System.out.printf("[Payment System] Processing Card Payment of $%.2f using card ending in %s (CVV: %s)...%n",
                amount, cardNumber.substring(Math.max(0, cardNumber.length() - 4)), maskedCvv);
        System.out.println("[Payment System] Card verification successful. Payment authorized.");
        return true;
    }
}
