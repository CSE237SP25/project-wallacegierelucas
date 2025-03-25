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
  
	private double balance;
	
	public BankAccount() {
		this.balance = 0;
	}
	
	public void deposit(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Must deposit a positive amount.");
		}
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Must withdraw a positive amount.");
		}
		
		if(balance < amount) {
			throw new InsufficientFundsException("Insufficient funds.");
		}
		
		this.balance -= amount;
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
}
