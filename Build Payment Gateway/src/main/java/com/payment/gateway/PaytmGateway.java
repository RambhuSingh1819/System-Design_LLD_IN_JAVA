package com.payment.gateway;

import com.payment.banking.BankingSystem;

/**
 * Concrete implementation of PaymentGateway for Paytm.
 */
public class PaytmGateway extends PaymentGateway {

    public PaytmGateway(BankingSystem bs) {
        super(bs);
    }

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        System.out.println("    [PaytmGateway] Validating Paytm payload...");
        // Paytm requirements: Amount must be positive.
        if (pr.getAmount() <= 0) {
            System.out.println("    [PaytmGateway] ❌ Validation Error: Amount must be greater than zero.");
            return false;
        }
        System.out.println("    [PaytmGateway] ✓ Paytm validation successful.");
        return true;
    }

    @Override
    protected void initializePayment(PaymentRequest pr) {
        System.out.println("    [PaytmGateway] Initializing Paytm transaction token and checksum utility...");
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        System.out.println("    [PaytmGateway] Confirming Paytm wallet debit and writing to ledger.");
    }
}
