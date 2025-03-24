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

    public BankAccount openAccount(double initialDeposit) {
        BankAccount newAccount = new BankAccount(initialDeposit);
        accounts.add(newAccount);
        return newAccount;
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
}
