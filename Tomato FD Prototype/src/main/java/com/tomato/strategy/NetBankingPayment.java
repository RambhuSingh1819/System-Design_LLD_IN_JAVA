package com.tomato.strategy;

public class NetBankingPayment implements IPaymentStrategy {
    private String bankName;

    public NetBankingPayment(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("[Payment System] Redirecting to %s NetBanking portal for amount $%.2f...%n", 
            bankName, amount);
        System.out.println("[Payment System] NetBanking secure authentication successful. Payment completed.");
        return true;
    }
}
