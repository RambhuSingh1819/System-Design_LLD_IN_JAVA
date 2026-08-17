package com.payment.controller;

import com.payment.gateway.GatewayFactory;
import com.payment.gateway.GatewayType;
import com.payment.gateway.PaymentException;
import com.payment.gateway.PaymentGateway;
import com.payment.gateway.PaymentGatewayProxy;
import com.payment.gateway.PaymentRequest;
import com.payment.service.PaymentService;

/**
 * Singleton Controller representing the client entrypoint.
 */
public class PaymentController {
    private static PaymentController instance;
    private final PaymentService ps;

    private PaymentController() {
        this.ps = PaymentService.getInstance();
    }

    /**
     * Get the single instance of PaymentController.
     */
    public static synchronized PaymentController getInstance() {
        if (instance == null) {
            instance = new PaymentController();
        }
        return instance;
    }

    public boolean handlePayment(GatewayType gt, PaymentRequest pr) {
        System.out.println("[PaymentController] Received handlePayment request for: " + gt);
        try {
            // 1. Resolve raw gateway from factory
            PaymentGateway rawPg = GatewayFactory.getInstance().getGateway(gt);

            // 2. Wrap gateway in Proxy to add retry capabilities (default 3 attempts)
            PaymentGateway proxiedPg = new PaymentGatewayProxy(rawPg, 3);

            // 3. Set the active gateway on PaymentService
            ps.setGateway(proxiedPg);

            // 4. Process payment
            return ps.processPayment(pr);
        } catch (PaymentException e) {
            System.out.println("[PaymentController] ❌ Transaction failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Overloaded method for testing/configuration that allows custom retry configurations.
     */
    public boolean handlePayment(GatewayType gt, PaymentRequest pr, int maxRetries) {
        System.out.println("[PaymentController] Received handlePayment request with custom retries=" + maxRetries + " for: " + gt);
        try {
            PaymentGateway rawPg = GatewayFactory.getInstance().getGateway(gt);
            PaymentGateway proxiedPg = new PaymentGatewayProxy(rawPg, maxRetries);
            ps.setGateway(proxiedPg);
            return ps.processPayment(pr);
        } catch (PaymentException e) {
            System.out.println("[PaymentController] ❌ Transaction failed: " + e.getMessage());
            return false;
        }
    }
}
