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
}