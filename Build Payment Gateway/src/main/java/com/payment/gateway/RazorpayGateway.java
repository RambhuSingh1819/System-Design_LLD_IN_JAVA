package com.payment.gateway;

import com.payment.banking.BankingSystem;

/**
 * Concrete implementation of PaymentGateway for Razorpay.
 */
public class RazorpayGateway extends PaymentGateway {

    public RazorpayGateway(BankingSystem bs) {
        super(bs);
    }

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        System.out.println("    [RazorpayGateway] Validating Razorpay payload...");
        // Razorpay requirements: Amount must be positive and currency must be INR or USD.
        if (pr.getAmount() <= 0) {
            System.out.println("    [RazorpayGateway] ❌ Validation Error: Amount must be greater than zero.");
            return false;
        }
        if (!"INR".equalsIgnoreCase(pr.getCurrency()) && !"USD".equalsIgnoreCase(pr.getCurrency())) {
            System.out.println("    [RazorpayGateway] ❌ Validation Error: Unsupported currency: " + pr.getCurrency());
            return false;
        }
        System.out.println("    [RazorpayGateway] ✓ Razorpay validation successful.");
        return true;
    }

    @Override
    protected void initializePayment(PaymentRequest pr) {
        System.out.println("    [RazorpayGateway] Initializing Razorpay order ID and secure keys...");
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        System.out.println("    [RazorpayGateway] Triggering Razorpay webhooks and storing transaction receipt.");
    }
}
