package com.safebank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void accountStartsWithZeroBalance() {
        Account acc = new Account();
        assertEquals(0.0, acc.getBalance());
    }

    @Test
    void depositPositiveAmountIncreasesBalance() {
        Account acc = new Account();
        assertEquals(0.0, acc.getBalance());
        acc.deposit(10.0);
        assertEquals(10.0, acc.getBalance());
    }

    @Test
    void depositNegativeDepositThrowsException() {
        Account acc = new Account();
        assertThrows(IllegalArgumentException.class, () -> {
            acc.deposit(-5.0);
        });
    }

    @Test
    void depositBalanceDoesNotChangeWhenNegativeDepositAttempt() {
        Account acc = new Account();
        assertEquals(0.0, acc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> {
            acc.deposit(-5.0);
        });
        assertEquals(0.0, acc.getBalance());
    }

    @Test
    void withdrawValidAmountDecreaseBalance() {
        Account acc = new Account();
        acc.deposit(100.0);
        assertEquals(100.0, acc.getBalance());
        acc.withdraw(50.0);
        assertEquals(50.0, acc.getBalance());
    }

    @Test
    void withdrawingMoreThanCurrentBalance() {
        Account acc = new Account();
        acc.deposit(100.0);
        assertEquals(100.0, acc.getBalance());
        assertThrows(IllegalStateException.class, () -> {
            acc.withdraw(101.0);
        });
        assertEquals(100.0, acc.getBalance());
    }

    @Test
    void withdrawNegativeAmountThrowsException() {
        Account acc = new Account();
        acc.deposit(100.0);
        assertEquals(100.0, acc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> {
            acc.withdraw(-100.0);
        });
        assertEquals(100.0, acc.getBalance());
    }

    @Test
    void withdrawExactBalanceBringsBalanceToZeroEdgeCase() {
        Account acc = new Account();
        acc.deposit(100.0);
        assertEquals(100.0, acc.getBalance());
        acc.withdraw(100.0);
        assertEquals(0.0, acc.getBalance());
    }

    @Test
    void depositTestDepositingSmallAmounts() {
        Account acc = new Account();
        acc.deposit(0.01);
        assertEquals(0.01, acc.getBalance(), 0.0001);
    }

    @Test
    void depositMultipleSmallAmountsToConfirmTotalAddsCorrectly() {
        Account acc = new Account();
        acc.deposit(0.01);
        assertEquals(0.01, acc.getBalance(), 0.0001);
        acc.deposit(0.02);
        assertEquals(0.03, acc.getBalance(), 0.0001);
    }

    @Test
    void depositAndImmediatelyWithdrawSameAmount() {
        Account acc = new Account();
        acc.deposit(50.0);
        acc.withdraw(50.0);
        assertEquals(0.0, acc.getBalance(), 0.0001);
    }

    @Test
    void accountHasNonNullAccountNumber() {
        Account acc = new Account();
        assertNotNull(acc.getAccountNumber(), "Account number should not be null");
    }

    @Test
    void accountHasValidAccountType() {
        Account acc = new Account();
        assertNotNull(acc.getAccountType(), "Account type should not be null");
    }

    @Test
    void accountStoresAndReturnsCorrectAccountType() {
        Account acc = new Account();
        acc.setAccountType(Account.AccountType.SAVINGS);
        assertEquals(Account.AccountType.SAVINGS, acc.getAccountType());
    }

    @Test
    void accountStartsWithAnEmptyTransactionLog() {
        Account acc = new Account();
        assertNotNull(acc.getTransactionLog(), "Transaction log should not be null");
        assertTrue(acc.getTransactionLog().isEmpty(), "Transaction log should be empty on new account");
    }

    @Test
    void depositAddsTransactionToLog() {
        Account acc = new Account();
        acc.deposit(100.0);
        List<Transaction> log = acc.getTransactionLog();
        assertEquals(1, log.size());
        Transaction t = log.get(0);
        assertEquals(Transaction.TransactionType.DEPOSIT, t.getType());
        assertEquals(100.0, t.getAmount(), 0.0001);
        assertEquals(100.0, t.getResultingBalance(), 0.0001);
    }
    
    @Test
    void withdrawAddsTransactionToLog() {
        Account acc = new Account();
        acc.deposit(100.0);
        acc.withdraw(50.0);
        List<Transaction> log = acc.getTransactionLog();
        assertEquals(2, log.size());
        Transaction t = log.get(1);
        assertEquals(Transaction.TransactionType.WITHDRAW, t.getType());
        assertEquals(50.0, t.getAmount(), 0.0001);
        assertEquals(50.0, t.getResultingBalance(), 0.0001);
    }
}