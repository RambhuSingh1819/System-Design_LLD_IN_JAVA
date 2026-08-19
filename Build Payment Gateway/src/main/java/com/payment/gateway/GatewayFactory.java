package com.payment.gateway;

import com.payment.banking.PaytmBankingSystem;
import com.payment.banking.RazorpayBankingSystem;

/**
 * Singleton Factory for instantiating Payment Gateways with their respective Banking System dependencies.
 */
public class GatewayFactory {
    private static GatewayFactory instance;

    private GatewayFactory() {}

    /**
     * Get the single instance of GatewayFactory.
     */
    public static synchronized GatewayFactory getInstance() {
        if (instance == null) {
            instance = new GatewayFactory();
        }
        return instance;
    }

    public PaymentGateway getGateway(GatewayType gt) {
        if (gt == null) {
            throw new IllegalArgumentException("GatewayType cannot be null.");
        }
        switch (gt) {
            case PAYTM:
                return new PaytmGateway(new PaytmBankingSystem());
            case RAZORPAY:
                return new RazorpayGateway(new RazorpayBankingSystem());
            default:
                throw new IllegalArgumentException("Unsupported gateway type: " + gt);
        }
    }
}
