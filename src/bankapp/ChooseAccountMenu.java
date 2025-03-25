package bankapp;

import java.util.List;
import java.util.Scanner;

public class ChooseAccountMenu {
	   Scanner scanner = new Scanner(System.in);
       
      
       System.out.println("Welcome, " + customer.getName() + "!");
       System.out.println("Which type of account would you like to manage?");
       System.out.println("1: Checking");
       System.out.println("2: Savings");
       System.out.print("Enter your selection (1 or 2): ");
       int accountTypeSelection = scanner.nextInt();
       
       
       String accountType;
       if(accountTypeSelection == 1) {
           accountType = "checking";
       } else if(accountTypeSelection == 2) {
           accountType = "savings";
       } else {
           System.out.println("Invalid selection. Exiting.");
           scanner.close();
           return;
       }
       
    
       List<BankAccount> filteredAccounts = customer.getAccountsByType(accountType);
       if(filteredAccounts.isEmpty()) {
           System.out.println("No " + accountType + " accounts available. Exiting.");
           scanner.close();
           return;
       }
       
      
       System.out.println("Your " + accountType + " accounts:");
       for (int i = 0; i < filteredAccounts.size(); i++) {
           System.out.println((i + 1) + ": Current Balance = " + filteredAccounts.get(i).getCurrentBalance());
       }

}
