package bankapp;

import java.util.ArrayList;
import java.util.List;


import java.util.Scanner;

import exceptions.InsufficientFundsException;

	public class Menu {
	    private List<BankAccount> accounts;
	    private Customer customer; 
		
		 public Menu(Customer customer) {
		        this.accounts = new ArrayList<>();
		        this.customer = customer;
		    
		    }
		 public void findAccount() {
		        Scanner scanner = new Scanner(System.in);
		        
		        String accountType = getAccountType(scanner);
		        if (accountType == null) {
		            scanner.close();
		            return;
		        }

		        List<BankAccount> filteredAccounts = customer.getAccountsByType(accountType);
		        if (filteredAccounts.isEmpty()) {
		            System.out.println("No " + accountType + " accounts available.");
		            scanner.close();
		            return;
		        }
		        
		        displayAccounts(accountType, filteredAccounts);
		        scanner.close();
		    }

		    private static String getAccountType(Scanner scanner) {
		        System.out.println("Which type of account would you like to manage?");
		        System.out.println("1: Checking");
		        System.out.println("2: Savings");
		        System.out.print("Enter your selection (1 or 2): ");
		        int accountTypeSelection = scanner.nextInt();

		        if (accountTypeSelection == 1) {
		            return "checking";
		        } else if (accountTypeSelection == 2) {
		            return "savings";
		        } else {
		            System.out.println("Invalid selection.");
		            return null;
		        }
		    }

		    private static void displayAccounts(String accountType, List<BankAccount> accounts) {
		        System.out.println("Your " + accountType + " accounts:");
		        for (int i = 0; i < accounts.size(); i++) {
		            System.out.println((i + 1));
		        }
		        
		    } 
		    
		    public BankAccount openAccount(double initialDeposit, String type) {
	 
		        BankAccount newAccount = new BankAccount(initialDeposit, type);
		        accounts.add(newAccount);
		        customer.addAccount(newAccount); 
		        return newAccount;
		     }
		    
		    public boolean closeAccount(BankAccount account) {
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

		    	    return accounts.remove(account);
		    }
		    
		    public static void transfer(BankAccount from, BankAccount to, double amount) {
		        System.out.println("\nInitiating transfer of " + amount + " from account " 
		                           + from + " to account " + to);
		        
		        
		        try {
		        	from.withdraw(amount);
		        	to.deposit(amount);
		            System.out.println("Transfer completed successfully.");
		        }
		        catch(InsufficientFundsException e) {
		        	 System.out.println("Transfer failed due to insufficient funds.");
		        }
		        
		    }

	}
