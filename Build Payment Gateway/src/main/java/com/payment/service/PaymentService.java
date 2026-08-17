package com.payment.service;

import com.payment.gateway.PaymentException;
import com.payment.gateway.PaymentGateway;
import com.payment.gateway.PaymentRequest;

/**
 * Singleton service representing the payment orchestrator context.
 */
public class PaymentService {
    private static PaymentService instance;
    private PaymentGateway pg;

    private PaymentService() {}

    /**
     * Get the single instance of PaymentService.
     */
    public static synchronized PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;
    }

    /**
     * Update/Set the active gateway strategy.
     * @param pg Concrete PaymentGateway or PaymentGatewayProxy
     */
    public void setGateway(PaymentGateway pg) {
        this.pg = pg;
    }

    /**
     * Executes the payment using the selected gateway.
     * @param pr The payment details
     * @return true if payment completes successfully
     * @throws PaymentException if processing fails
     */
    public boolean processPayment(PaymentRequest pr) throws PaymentException {
        if (pg == null) {
            throw new PaymentException("Active payment gateway not set. Cannot process payment.");
        }
        return pg.processPayment(pr);
    }

    /**
     * Retrieve the currently set gateway.
     */
    public PaymentGateway getGateway() {
        return pg;
    }
}
