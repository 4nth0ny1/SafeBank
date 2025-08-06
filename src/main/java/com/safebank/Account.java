package com.safebank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private String accountNumber;
    public enum AccountType {
        CHECKING,
        SAVINGS
    };
    private AccountType accountType;
    private List<Transaction> transactionLog = new ArrayList<>();


    public Account() {
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber(); // if applicable
        this.accountType = AccountType.CHECKING; // or whatever default you want
    }

    public List<Transaction> getTransactionLog() {
        return transactionLog;
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis(); // simple placeholder
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType type) {
        this.accountType = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance += amount;
        transactionLog.add(new Transaction(
                amount,
                Transaction.TransactionType.DEPOSIT,
                LocalDateTime.now().toString(),
                balance
        ));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Withdraw must be less or equal to the current balance.");
        }
        balance -= amount;
        transactionLog.add(new Transaction(
                amount,
                Transaction.TransactionType.WITHDRAW,
                LocalDateTime.now().toString(),
                balance
        ));
    }
}
