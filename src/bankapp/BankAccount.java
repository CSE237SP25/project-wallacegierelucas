package bankapp;

import java.util.Scanner;

import exceptions.InsufficientFundsException;
import java.util.ArrayList; 
import java.util.List;

public class BankAccount {
	
	private double balance;
    private String type;
    private String accountId;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance, String type, String accountId) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
        this.type = type;
        this.accountId = accountId;
        this.transactionHistory = new ArrayList<>(); 
        addTransaction("Account created with ID: " + accountId);
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
		addTransaction("Deposited $ "+ amount + " | New Balance: $" + balance);
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
		addTransaction("Withdrew $" + amount + " | New Balance: $" + balance);
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public void addTransaction(String description) { 
		transactionHistory.add(description);
	}
	
	public List<String> getTransactionHistory(){ 
		return transactionHistory;
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


