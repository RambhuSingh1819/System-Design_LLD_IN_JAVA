package com.payment;

import com.payment.banking.PaytmBankingSystem;
import com.payment.banking.RazorpayBankingSystem;
import com.payment.controller.PaymentController;
import com.payment.gateway.*;
import com.payment.service.PaymentService;

/**
 * Interactive CLI / Demonstration wrapper to visualize LLD execution.
 */
public class Main {
    // Formatting Colors
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        System.out.println(PURPLE + "===========================================================" + RESET);
        System.out.println(PURPLE + "      WELCOME TO THE PAYMENT GATEWAY LLD PROTOTYPE        " + RESET);
        System.out.println(PURPLE + "===========================================================" + RESET);
        System.out.println(CYAN + "Design Patterns Implemented:" + RESET);
        System.out.println(" 1. " + BLUE + "Singleton Pattern" + RESET + " (GatewayFactory, PaymentService, PaymentController)");
        System.out.println(" 2. " + BLUE + "Factory Pattern" + RESET + " (GatewayFactory resolving Gateway Types)");
        System.out.println(" 3. " + BLUE + "Template Method Pattern" + RESET + " (PaymentGateway defining the step-by-step payment flow)");
        System.out.println(" 4. " + BLUE + "Proxy Pattern" + RESET + " (PaymentGatewayProxy adding transparent error handling & retries)");
        System.out.println(PURPLE + "===========================================================" + RESET);

        PaymentController controller = PaymentController.getInstance();

        // ----------------------------------------------------
        // Scenario 1: Paytm Direct Success
        // ----------------------------------------------------
        System.out.println("\n" + YELLOW + ">>> SCENARIO 1: Standard Successful Paytm Payment Request <<<" + RESET);
        PaymentRequest req1 = new PaymentRequest("User_101", "Merchant_XYZ", 1250.00, "INR");
        System.out.println("Request: " + req1);
        
        boolean res1 = controller.handlePayment(GatewayType.PAYTM, req1);
        if (res1) {
            System.out.println(GREEN + "✓ SCENARIO 1 SUCCESS: Payment completed successfully on the first try." + RESET);
        } else {
            System.out.println(RED + "❌ SCENARIO 1 FAILURE: Paytm transaction failed." + RESET);
        }

        // ----------------------------------------------------
        // Scenario 2: Razorpay Transient Failure & Recovery (Retry Demonstration)
        // ----------------------------------------------------
        System.out.println("\n" + YELLOW + ">>> SCENARIO 2: Razorpay Payment with Transient Banking Failures <<<" + RESET);
        PaymentRequest req2 = new PaymentRequest("User_202", "Merchant_XYZ", 450.00, "INR");
        System.out.println("Request: " + req2);
        System.out.println(CYAN + "[Setup] Configuring the Razorpay bank network to fail twice due to timeout." + RESET);

        // For this scenario, we'll configure a specific gateway with failures manually, bypassing the standard factory
        // to showcase the exact interaction of the proxy with transient errors.
        RazorpayBankingSystem failingBank = new RazorpayBankingSystem();
        failingBank.setSimulatedFailures(2); // First two processPayment calls will fail
        
        RazorpayGateway rawRazorpay = new RazorpayGateway(failingBank);
        // Create Proxy with 3 max retries
        PaymentGatewayProxy retryProxy = new PaymentGatewayProxy(rawRazorpay, 3);

        // Inject the proxied gateway into our singleton service context
        PaymentService.getInstance().setGateway(retryProxy);

        try {
            boolean res2 = PaymentService.getInstance().processPayment(req2);
            if (res2) {
                System.out.println(GREEN + "✓ SCENARIO 2 SUCCESS: Payment processed successfully after " + retryProxy.getActualRetriesRun() + " retries." + RESET);
            }
        } catch (PaymentException e) {
            System.out.println(RED + "❌ SCENARIO 2 FAILURE: " + e.getMessage() + RESET);
        }

        // ----------------------------------------------------
        // Scenario 3: Validation Rejection
        // ----------------------------------------------------
        System.out.println("\n" + YELLOW + ">>> SCENARIO 3A: Validation Rejection (Razorpay Unsupported Currency) <<<" + RESET);
        PaymentRequest req3a = new PaymentRequest("User_303", "Merchant_XYZ", 99.00, "EUR");
        System.out.println("Request: " + req3a);
        
        boolean res3a = controller.handlePayment(GatewayType.RAZORPAY, req3a);
        if (!res3a) {
            System.out.println(GREEN + "✓ SCENARIO 3A SUCCESS: Validation correctly rejected the request before routing to banking system." + RESET);
        } else {
            System.out.println(RED + "❌ SCENARIO 3A FAILURE: Invalid currency transaction was processed." + RESET);
        }

        // ----------------------------------------------------
        // Scenario 4: Permanent Network Failure (Exceeding Max Retries)
        // ----------------------------------------------------
        System.out.println("\n" + YELLOW + ">>> SCENARIO 3B: Permanent Bank Network Failure (Exceeds Retry Limits) <<<" + RESET);
        PaymentRequest req3b = new PaymentRequest("User_404", "Merchant_XYZ", 8500.00, "INR");
        System.out.println("Request: " + req3b);
        System.out.println(CYAN + "[Setup] Configuring the Paytm bank network to fail 4 times consecutively." + RESET);

        PaytmBankingSystem offlineBank = new PaytmBankingSystem();
        offlineBank.setSimulatedFailures(4); // Exceeds the max 3 attempts limit
        
        PaytmGateway rawPaytm = new PaytmGateway(offlineBank);
        PaymentGatewayProxy strictProxy = new PaymentGatewayProxy(rawPaytm, 3);
        
        PaymentService.getInstance().setGateway(strictProxy);

        try {
            PaymentService.getInstance().processPayment(req3b);
            System.out.println(RED + "❌ SCENARIO 3B FAILURE: Payment processed despite severe network outage." + RESET);
        } catch (PaymentException e) {
            System.out.println(GREEN + "✓ SCENARIO 3B SUCCESS: Proxy aborted payment after limit and threw: " + e.getMessage() + RESET);
        }

        System.out.println("\n" + PURPLE + "===========================================================" + RESET);
        System.out.println(PURPLE + "      DEMONSTRATION OF PAYMENT GATEWAY LLD COMPLETED      " + RESET);
        System.out.println(PURPLE + "===========================================================" + RESET);
    }
}
