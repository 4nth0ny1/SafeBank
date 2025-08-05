package com.safebank;

public class Account {
    private double balance;

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
