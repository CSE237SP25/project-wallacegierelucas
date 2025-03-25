package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import bankapp.BankAccount;
import bankapp.Menu;

import java.io.*;
import java.util.*;

public class MenuTests {
	
	  private final PrintStream originalOut = System.out;
	  private final InputStream originalIn = System.in;
	  private ByteArrayOutputStream testOut;


	  
	@Test
    public void testInvalidSelection() {
		testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
	
        String input = "3\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Customer customer = new Customer();

        
        Menu.manageAccount(customer);

        
        String output = testOut.toString();
        assertTrue(output.contains("Invalid selection."), "Output should indicate an invalid selection");
        
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testNoAccountsAvailable() {
    	testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
    	
        String input = "1\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Customer customer = new Customer();

        
        Menu.manageAccount(customer);

       
        String output = testOut.toString();
        assertTrue(output.contains("No checking accounts available."), "Output should indicate that no checking accounts are available");
        
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    void testOpenAccount() {
    	Customer customer = new Customer();
        BankAccount account = customer.openAccount(500.0);

        assertNotNull(account);
        assertEquals(500.0, account.getCurrentBalance(), 0.01);
        assertEquals(1, customer.getAccounts().size(), "Customer should have one account after opening.");
        assertTrue(customer.getAccounts().contains(account), "Customer's account list should include the new account.");
    }
    
    @Test
    void testMultipleAccounts() {
    	Customer customer = new Customer();
        BankAccount account1 = customer.openAccount(200.0);
        BankAccount account2 = customer.openAccount(300.0);

        List<BankAccount> accounts = customer.getAccounts();
        assertEquals(2, accounts.size(), "Customer should have two accounts.");
        assertTrue(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
    }
}


}
