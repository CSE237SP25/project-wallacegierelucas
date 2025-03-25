package bankapp;

import java.util.Scanner;

import exceptions.InvalidMenuOptionException;

public class BankAccountMenu {
	private static final int CHECK_BALANCE = 1;
	private static final int DEPOSIT = 2;
	private static final int WITHDRAW = 3;
	
	private BankAccount account;
	private Scanner keyboardInput;
	
	public BankAccountMenu() {
		this.account =  new BankAccount();
		this.keyboardInput = new Scanner(System.in);
	}
	
	public void displayOptions() {
		System.out.println("Menu Options:");
		System.out.println("1. Check Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("Enter your choice (1-3):");
	}
	
	public int getUserOptionInput() {
		return keyboardInput.nextInt();
	}
	
	public double getUserAmountInput(String prompt) {
		System.out.println(prompt);
		return keyboardInput.nextDouble();
	}
	
	public void processUserOptionInput(int option) {
		switch(option) {
			case CHECK_BALANCE:
				processCheckBalance();
				break;
			case DEPOSIT:
				double depositAmount = getUserAmountInput("Enter deposit amount:");
				processDeposit(depositAmount);
				break;
			case WITHDRAW:
				double withdrawalAmount = getUserAmountInput("Enter withdrawal amount:");
				processWithdrawal(withdrawalAmount);
				break;
			default:
				throw new InvalidMenuOptionException("Must enter a valid menu option.");
		}
	}	
	
	public void processDeposit(double amount) {
		account.deposit(amount);
	}
	
	public void processWithdrawal(double amount) {
		account.withdraw(amount);
	}
	
	public void processCheckBalance() {
		System.out.println("Current Balance: " + account.getCurrentBalance());
	}
	
	public BankAccount getAccount() {
		return account;
	}
}
