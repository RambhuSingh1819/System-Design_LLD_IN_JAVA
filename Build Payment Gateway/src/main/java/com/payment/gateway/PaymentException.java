package com.payment.gateway;

/**
 * Custom Exception thrown when validation, connection, or bank processing fails.
 */
public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
