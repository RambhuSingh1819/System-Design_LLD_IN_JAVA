package com.tomato.strategy;

public class UpiPayment implements IPaymentStrategy {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("[Payment System] Requesting UPI payment of $%.2f from %s...%n", 
            amount, upiId);
        System.out.println("[Payment System] UPI transaction approved by user. Payment received.");
        return true;
    }
}
