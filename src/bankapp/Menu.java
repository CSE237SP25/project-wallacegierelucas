package bankapp;

import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;

import exceptions.InsufficientFundsException;

public class Menu {
	private List<BankAccount> accounts;
	private Customer customer; 
	private Scanner scanner;

	public Menu(Customer customer) {
		this.accounts = new ArrayList<>();
		this.customer = customer;
		this.scanner = new Scanner(System.in);
	}
	
	public BankAccount findAccount(String accountId) {
		for(int i = 0; i < accounts.size(); i++) {
			BankAccount account = accounts.get(i);
			if(account.getAccountId().compareTo(accountId) == 0) {
				return account;
			}
		}
		
		throw new IllegalArgumentException("Invalid account id");
	}

	private String getAccountType() {
		System.out.println("Which type of account would you like to manage?");
		System.out.println("1: Checking");
		System.out.println("2: Savings");
		System.out.print("Enter your selection (1 or 2): ");
		int accountTypeSelection = scanner.nextInt();
		scanner.nextLine();

		if (accountTypeSelection == 1) {
			return "checking";
		} else if (accountTypeSelection == 2) {
			return "savings";
		} else {
			throw new IllegalArgumentException("Invalid selection");
		}
	}

	private void displayAccounts(String accountType) {
		System.out.println("Your " + accountType + " accounts:");
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i).toString());
		}
	} 

	public BankAccount openAccount() {
		String type = getAccountType();
		
		System.out.println("Enter a unique id for this account.");
		String accountId = scanner.nextLine();

		System.out.println("How much would you like to deposit initially?");
		int initialDeposit = scanner.nextInt();
		scanner.nextLine();
		
		BankAccount newAccount = new BankAccount(initialDeposit, type, accountId);

		accounts.add(newAccount);
		customer.addAccount(newAccount); 
		return newAccount;
	}

	public BankAccount selectAccount() {
		String type = getAccountType();
		displayAccounts(type);
		
		System.out.println("Enter the account id.");
		String closeAccountId = scanner.nextLine();
		return findAccount(closeAccountId);
	}
	
	public boolean closeAccount() {
		BankAccount account = selectAccount();
		
		if (account == null) {
			System.out.println("Invalid account.");
			return false;
		}

		if (!accounts.contains(account)) {
			System.out.println("Account not found.");
			return false;
		}

		if (account.getCurrentBalance() != 0) {
			System.out.println("Cannot close account. Please withdraw all funds first.");
			return false;
		}
		customer.removeAccount(account);
		return accounts.remove(account);
	}

	public void transfer() {
		System.out.println("Enter the id of the account to transfer money from:");
		String transferFromId = scanner.nextLine();
		BankAccount transferFromAccount = findAccount(transferFromId);
		
		System.out.println("Enter the id of the account to transfer money to:");
		String transferToId = scanner.nextLine();
		BankAccount transferToAccount = findAccount(transferToId);
		
		System.out.println("How much would you like to transfer?");
		int transferAmount = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("\nInitiating transfer of " + transferAmount + " from account " 
				+ transferFromAccount + " to account " + transferToAccount);
		try {
			transferFromAccount.withdraw(transferAmount);
			transferToAccount.deposit(transferAmount);
			System.out.println("Transfer completed successfully.");
		}
		catch(InsufficientFundsException e) {
			System.out.println("Transfer failed due to insufficient funds.");
		}
	}
	
	public void displayMenuOptions() {
		System.out.println("What would you like to do?");
		System.out.println("1. Open an account");
		System.out.println("2. Close an account");
		System.out.println("3. Transfer between accounts");
		System.out.println("4. Choose an account to manage");
		System.out.println("5. Exit");
		
	}
	
	public boolean getMenuOptionInput() {
		int menuOptionSelection = scanner.nextInt();
		scanner.nextLine();

		if (menuOptionSelection == 1) {
			openAccount();
		} else if (menuOptionSelection == 2) {
			closeAccount();
		} else if(menuOptionSelection == 3) {
			transfer();
		}
		else if(menuOptionSelection == 4) {
			BankAccount account = selectAccount();
			BankAccountMenu bankAccountMenu = new BankAccountMenu(account);
			bankAccountMenu.manageAccount();
		}
		else if(menuOptionSelection == 5) {
			 return true; 
		}
		else {
			throw new IllegalArgumentException();
		}
		return false; 
	}
}
