package bankapp;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity {
	
	
	private Map<String, BankAccount> unfrozenAccounts;
    private Map<String, BankAccount> frozenAccounts;
    
    public AccountActivity() {
        unfrozenAccounts = new HashMap<>();
        frozenAccounts = new HashMap<>();
    }
    
    public void addAccount(BankAccount account) {
        if (account != null && account.getAccountId() != null) {
            unfrozenAccounts.put(account.getAccountId(), account);
        }
    }
    
    public void freezeAccount(String accountId) {
        BankAccount account = unfrozenAccounts.remove(accountId);
        if (account != null) {
            frozenAccounts.put(accountId, account);
            account.getTransactions().add(new Transaction("Freeze", 0));
            System.out.println("Account " + accountId + " has been frozen.");
        } else {
            System.out.println("Account " + accountId + " not found in active accounts or is already frozen.");
        }
        
    }
    public void unfreezeAccount(String accountId) {
        BankAccount account = frozenAccounts.remove(accountId);
        if (account != null) {
            unfrozenAccounts.put(accountId, account);
            account.getTransactions().add(new Transaction("Unfreeze", 0));
            System.out.println("Account " + accountId + " has been unfrozen.");
        } else {
            System.out.println("Account " + accountId + " not found in frozen accounts.");
        }
    }

    public void deposit(String accountId, double amount) {
        if (frozenAccounts.containsKey(accountId)) {
            System.out.println("Cannot deposit to frozen account " + accountId + ".");
            return;
        }
        BankAccount account = unfrozenAccounts.get(accountId);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " into account " + accountId + ".");
        } else {
            System.out.println("Account " + accountId + " not found.");
        }
    }

    
    public void withdraw(String accountId, double amount) {
        if (frozenAccounts.containsKey(accountId)) {
            System.out.println("Cannot withdraw from frozen account " + accountId + ".");
            return;
        }
        BankAccount account = unfrozenAccounts.get(accountId);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Withdrew $" + amount + " from account " + accountId + ".");
            } catch (Exception e) {
                System.out.println("Withdrawal failed: " + e.getMessage());
            }
        } else {
            System.out.println("Account " + accountId + " not found.");
        }
    }
}

