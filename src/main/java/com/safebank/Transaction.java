package com.safebank;

public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    private final double amount;
    private final TransactionType type;
    private final String timestamp;
    private final double resultingBalance;

    public Transaction(double amount, TransactionType type, String timestamp, double resultingBalance) {
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.resultingBalance = resultingBalance;
    }

    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public String getTimestamp() { return timestamp; }
    public double getResultingBalance() { return resultingBalance; }
}
