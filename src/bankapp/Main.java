package bankapp;

import exceptions.InsufficientFundsException;

public class Main {
    public static void main(String[] args) {
        
        Customer customer = new Customer("Taylor Swift", "001");
        
   
        Menu menu = new Menu(customer);
        
    
        BankAccount account = menu.openAccount(1000.0, "savings");
        System.out.println("Opened account: " + account);
        
       
        account.deposit(500.0);
        System.out.println("After depositing $500, balance is: $" + account.getCurrentBalance());
        
      
        try {
            account.withdraw(300.0);
            System.out.println("After withdrawing $300, balance is: $" + account.getCurrentBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal error: " + e.getMessage());
        }
        
      
        boolean closed = menu.closeAccount(account);
        System.out.println("Attempt to close account with non-zero balance: " + closed);
        
      
        try {
            double remainingBalance = account.getCurrentBalance();
            account.withdraw(remainingBalance);
            System.out.println("After withdrawing all funds, balance is: $" + account.getCurrentBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error withdrawing funds: " + e.getMessage());
        }
        
      
        closed = menu.closeAccount(account);
        System.out.println("Attempt to close account after zeroing balance: " + closed);
        
      
        System.out.println("Customer info: " + customer);
    }
}
