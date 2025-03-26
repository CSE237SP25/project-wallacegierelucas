package bankapp;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String customerId;
    private List<BankAccount> accounts;

    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
        this.accounts = new ArrayList<>();
    }
    
    public List<BankAccount> getAccounts() {
        return accounts;
    }
    
    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', customerId='" + customerId + "', accounts=" + accounts + "}";
    }
    
    public List<BankAccount> getAccountsByType(String accountType) {
        List<BankAccount> filtered = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account.getType().equalsIgnoreCase(accountType)) {
                filtered.add(account);
            }
        }
        return filtered;
    }
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
    public void removeAccount(BankAccount account) {
    	accounts.remove(account);
    }

}

