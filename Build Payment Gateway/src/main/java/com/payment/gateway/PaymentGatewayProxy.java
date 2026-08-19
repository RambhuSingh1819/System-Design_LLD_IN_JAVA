package com.payment.gateway;

/**
 * Proxy class for PaymentGateway to provide error handling and automatic
 * transaction retries.
 */
public class PaymentGatewayProxy extends PaymentGateway {
    private final PaymentGateway realPg;
    private final int maxRetries;
    private int actualRetriesRun = 0; // Expose for testing/monitoring assertions

    public PaymentGatewayProxy(PaymentGateway realPg, int maxRetries) {
        super(realPg.getBankingSystem());
        this.realPg = realPg;
        this.maxRetries = maxRetries;
    }

    @Override
    public boolean processPayment(PaymentRequest pr) throws PaymentException {
        actualRetriesRun = 0;
        int attempts = 0;

        while (true) {
            try {
                attempts++;
                if (attempts > 1) {
                    System.out.println("  [PaymentGatewayProxy] Retrying transaction. Attempt " + attempts + " of "
                            + maxRetries + "...");
                    actualRetriesRun++;
                }
                return realPg.processPayment(pr);
            } catch (PaymentException e) {
                System.out.println("  [PaymentGatewayProxy] Failure on attempt " + attempts + ": " + e.getMessage());

                if (attempts >= maxRetries) {
                    System.out.println("  [PaymentGatewayProxy] Max retries (" + maxRetries
                            + ") reached. Payment failed permanently.");
                    throw new PaymentException(
                            "Payment failed after " + maxRetries + " attempts. Reason: " + e.getMessage(), e);
                }

                // Sleep briefly before retrying
                try {
                    System.out.println("  [PaymentGatewayProxy] Waiting 200ms before next retry...");
                    Thread.sleep(200);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new PaymentException("Payment interrupted during retry sleep", ie);
                }
            }
        }
    }

    @Override
    protected boolean validatePayment(PaymentRequest pr) {
        return realPg.validatePayment(pr);
    }

    @Override
    protected void initializePayment(PaymentRequest pr) {
        realPg.initializePayment(pr);
    }

    @Override
    protected void confirmPayment(PaymentRequest pr) {
        realPg.confirmPayment(pr);
    }

    public PaymentGateway getRealPg() {
        return realPg;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public int getActualRetriesRun() {
        return actualRetriesRun;
    }
}
