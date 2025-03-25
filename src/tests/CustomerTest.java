package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.Customer;
import bankapp.BankAccount;

import java.util.List;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Nicole Lucas", "CUST123");
    }

    @Test
    void testCustomerCreation() {
        assertEquals("Nicole Lucas", customer.getName());
        assertEquals("CUST123", customer.getCustomerId());
        assertTrue(customer.getAccounts().isEmpty(), "New customer should have no accounts initially.");
    }

    @Test
    void testOpenAccount() {
        BankAccount account = customer.openAccount(500.0);

        assertNotNull(account);
        assertEquals(500.0, account.getCurrentBalance(), 0.01);
        assertEquals(1, customer.getAccounts().size(), "Customer should have one account after opening.");
        assertTrue(customer.getAccounts().contains(account), "Customer's account list should include the new account.");
    }

    @Test
    void testMultipleAccounts() {
        BankAccount account1 = customer.openAccount(200.0);
        BankAccount account2 = customer.openAccount(300.0);

        List<BankAccount> accounts = customer.getAccounts();
        assertEquals(2, accounts.size(), "Customer should have two accounts.");
        assertTrue(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
    }

    @Test
    void testCloseAccountWithZeroBalance() {
        BankAccount account = customer.openAccount(0.0);

        boolean result = customer.closeAccount(account);

        assertTrue(result, "Account with zero balance should be closed.");
        assertFalse(customer.getAccounts().contains(account), "Closed account should be removed from the customer’s account list.");
    }

    @Test
    void testCloseAccountWithNonZeroBalance() {
        BankAccount account = customer.openAccount(100.0);

        boolean result = customer.closeAccount(account);

        assertFalse(result, "Account with nonzero balance should not be closed.");
        assertTrue(customer.getAccounts().contains(account), "Account should still exist in the customer’s account list.");
    }

    @Test
    void testCloseNonExistentAccount() {
        BankAccount account1 = customer.openAccount(50.0);
        BankAccount account2 = new BankAccount(0.0); // This account is NOT added to the customer

        boolean result = customer.closeAccount(account2);

        assertFalse(result, "Closing an account that doesn't exist should return false.");
    }
}
