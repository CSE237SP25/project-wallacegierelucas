package bankapp;

import java.util.Scanner;

import exceptions.InsufficientFundsException;

public class BankAccount {
	
	private double balance;
    private String type;
    private String accountId;

    public BankAccount(double initialBalance, String type, String accountId) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
        this.type = type;
        this.accountId = accountId;
    }
  	
	public BankAccount() {
		this.balance = 0;
	}
	
	public void deposit(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Must deposit a positive amount.");
		}
		 if (amount > 1000) {
		        Scanner userInputDeposit = new Scanner(System.in);
		        System.out.println("Attention: You are about to deposit a large amount: $" + amount);
		        System.out.print("Do you want to proceed? (yes/no): ");
		        String response = userInputDeposit.nextLine();
		        if (!response.equalsIgnoreCase("yes")) {
		            System.out.println("Deposit canceled.");
		            return;
		        }
		 }
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Must withdraw a positive amount.");
		}
		 if (amount > 1000) {
	            Scanner userInputWithdraw = new Scanner(System.in);
	            System.out.println("Attention: You are about to withdraw a large amount: $" + amount);
	            System.out.print("Do you want to proceed? (yes/no): ");
	            String response = userInputWithdraw.nextLine();
	            if (!response.equalsIgnoreCase("yes")) {
	                System.out.println("Withdrawal canceled.");
	                return;
	            }
	        }
		if(balance < amount) {
			throw new InsufficientFundsException("Insufficient funds.");
		}
		
		this.balance -= amount;
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
    @Override
    public String toString() {
        return "BankAccount{accountId=" + accountId + "}" ;
    }

	public String getType() {
		return this.type;	
	}
	 
	public String getAccountId() {
		return this.accountId;
	}
	
	    
	}


