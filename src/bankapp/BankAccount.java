package bankapp;

import exceptions.InsufficientFundsException;

public class BankAccount {
    private double balance;
    private Customer owner;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    @Override
    public String toString() {
        return "BankAccount{balance=" + balance + "}";
    }
}
