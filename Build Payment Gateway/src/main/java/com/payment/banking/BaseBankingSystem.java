package com.payment.banking;

/**
 * Base implementation of BankingSystem with built-in failure simulation capabilities
 * to help demonstrate LLD retry and recovery logic.
 */
public abstract class BaseBankingSystem implements BankingSystem {
    private final String bankName;
    private int failureCountLeft = 0;

    protected BaseBankingSystem(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Configures a specific number of transient failures for the next payments.
     * @param count The number of consecutive calls that should fail before succeeding.
     */
    public void setSimulatedFailures(int count) {
        this.failureCountLeft = count;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("  [" + bankName + "] Processing payment of " + amount + "...");
        if (failureCountLeft > 0) {
            System.out.println("  [" + bankName + "] ❌ Network Timeout/System Down! (Simulated failures remaining: " + failureCountLeft + ")");
            failureCountLeft--;
            return false;
        }
        System.out.println("  [" + bankName + "] ✓ Bank cleared the transaction successfully.");
        return true;
    }
}
