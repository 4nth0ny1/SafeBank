package com.safebank;

public class Account {
    private double balance;
    private String accountNumber;
    public enum AccountType {
        CHECKING,
        SAVINGS
    };

    private final AccountType accountType;

    public Account() {
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber(); // if applicable
        this.accountType = AccountType.CHECKING; // or whatever default you want
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis(); // simple placeholder
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {}

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Withdraw must be less or equal to the current balance.");
        }
        balance -= amount;
    }
}
