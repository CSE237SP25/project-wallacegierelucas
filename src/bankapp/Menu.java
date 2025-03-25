package bankapp;
import java.util.List;
import java.util.Scanner;

	public class Menu {
		 public static void findAccount(Customer customer) {
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
		}
	
