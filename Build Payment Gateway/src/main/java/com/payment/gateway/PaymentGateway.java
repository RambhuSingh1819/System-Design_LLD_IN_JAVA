package com.payment.gateway;

import com.payment.banking.BankingSystem;

/**
 * Abstract Payment Gateway containing the template method for payment flows.
 */
public abstract class PaymentGateway {
    protected final BankingSystem bs;

    protected PaymentGateway(BankingSystem bs) {
        this.bs = bs;
    }

    /**
     * Template Method that outlines the strict sequence of steps for processing a payment.
     * Declared as 'final' to prevent subclasses from modifying the invariant algorithm steps.
     * 
     * @param pr The payment request parameters
     * @return true if successful
     * @throws PaymentException if any validation, initialization, or execution step fails
     */
    public boolean processPayment(PaymentRequest pr) throws PaymentException {
        System.out.println("  [PaymentGateway] Starting Payment Flow for: " + pr);

        // Step 1: Validate payment inputs
        System.out.println("  [PaymentGateway] Step 1: Validating transaction inputs...");
        if (!validatePayment(pr)) {
            throw new PaymentException("Payment Request validation failed!");
        }

        // Step 2: Initialize transaction inside the gateway
        System.out.println("  [PaymentGateway] Step 2: Initializing transaction on gateway...");
        initializePayment(pr);

        // Step 3: Run payment through the banking system
        System.out.println("  [PaymentGateway] Step 3: Sending request to the Bank Network...");
        boolean bankResult = bs.processPayment(pr.getAmount());
        if (!bankResult) {
            throw new PaymentException("Bank transaction execution failed!");
        }

        // Step 4: Confirm transaction completion
        System.out.println("  [PaymentGateway] Step 4: Confirming payment receipt...");
        confirmPayment(pr);

        System.out.println("  [PaymentGateway] Transaction completed successfully.");
        return true;
    }

    // Hook/Step methods to be implemented by concrete gateways
    protected abstract boolean validatePayment(PaymentRequest pr);
    protected abstract void initializePayment(PaymentRequest pr);
    protected abstract void confirmPayment(PaymentRequest pr);

    /**
     * Expose banking system reference for proxies/decorators.
     */
    public BankingSystem getBankingSystem() {
        return bs;
    }
}
