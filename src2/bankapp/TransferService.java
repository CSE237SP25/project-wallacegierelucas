package bankapp;

public class TransferService {
	public static void transfer(BankAccount from, BankAccount to, double amount) {
        System.out.println("\nInitiating transfer of " + amount + " from account " 
                           + from.getAccountID() + " to account " + to.getAccountID());
        if (from.withdraw(amount)) {
            to.deposit(amount);
            System.out.println("Transfer completed successfully.");
        } else {
            System.out.println("Transfer failed due to insufficient funds.");
        }
    }
}
