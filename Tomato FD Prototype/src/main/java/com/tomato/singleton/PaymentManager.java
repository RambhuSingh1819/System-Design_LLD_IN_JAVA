package com.tomato.singleton;

import com.tomato.strategy.IPaymentStrategy;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {
    private static PaymentManager instance;
    private final List<String> paymentLogs;

    private PaymentManager() {
        paymentLogs = new ArrayList<>();
    }

    public static PaymentManager getInstance() {
        if (instance == null) {
            synchronized (PaymentManager.class) {
                if (instance == null) {
                    instance = new PaymentManager();
                }
            }
        }
        return instance;
    }

    public boolean processPayment(double amount, IPaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        boolean success = strategy.pay(amount);
        if (success) {
            String log = String.format("Payment of $%.2f processed successfully via %s", 
                amount, strategy.getClass().getSimpleName());
            synchronized (paymentLogs) {
                paymentLogs.add(log);
            }
        }
        return success;
    }

    public List<String> getPaymentLogs() {
        synchronized (paymentLogs) {
            return new ArrayList<>(paymentLogs); 
        }
    }

    public void clear() {
        synchronized (paymentLogs) {
            paymentLogs.clear();
        }
    }
}
