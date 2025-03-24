package bankapp;


public class BankAccount {
    private double balance;
    private Customer owner;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
    }

    public double getCurrentBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return "BankAccount{balance=" + balance + "}";
    }
}
