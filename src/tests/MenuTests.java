package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import bankapp.BankAccount;
import bankapp.Customer;
import bankapp.Menu;

import java.io.*;
import java.util.*;

public class MenuTests {
	
	  private final PrintStream originalOut = System.out;
	  private final InputStream originalIn = System.in;
	  private ByteArrayOutputStream testOut;
	  private Menu menu; 
	  private Customer customer; 


	  
	@Test
    public void testInvalidSelection() {
		testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
	    Customer customer = new Customer("Ella", "123");
	    Menu menu = new Menu(customer);
	
        String input = "3\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        
        menu.findAccount();

        
        String output = testOut.toString();
        assertTrue(output.contains("Invalid selection."), "Output should indicate an invalid selection");
        
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testNoAccountsAvailable() {
    	testOut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(testOut));
	    Customer customer = new Customer("Emily", "124");
	    Menu menu = new Menu(customer);
    	
        String input = "1\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        

        
        menu.findAccount();

       
        String output = testOut.toString();
        assertTrue(output.contains("No checking accounts available."), "Output should indicate that no checking accounts are available");
        
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    public void testOpenAccount() {
    	Customer customer = new Customer("Emma", "125");;
        Menu menu = new Menu(customer); 
    	BankAccount account = menu.openAccount(500.0, "checking");

        assertNotNull(account);
        assertEquals(500.0, account.getCurrentBalance(), 0.01);
        assertEquals(1, customer.getAccounts().size(), "Customer should have one account after opening.");
        assertTrue(customer.getAccounts().contains(account), "Customer's account list should include the new account.");
    }
    
    @Test
    public void testMultipleAccounts() {
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account1 = menu.openAccount(200.0, "checking");
        BankAccount account2 = menu.openAccount(300.0, "checking");

        List<BankAccount> accounts = customer.getAccounts();
        assertEquals(2, accounts.size(), "Customer should have two accounts.");
        assertTrue(accounts.contains(account1));
        assertTrue(accounts.contains(account2));
    }
    @Test
    void testCloseAccountWithZeroBalance() {
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account = menu.openAccount(0.0, "checking");

        boolean result = menu.closeAccount(account);

        assertTrue(result, "Account with zero balance should be closed.");
        assertFalse(customer.getAccounts().contains(account), "Closed account should be removed from the customer’s account list.");
    }

    @Test
    void testCloseAccountWithNonZeroBalance() {
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account = menu.openAccount(100.0, "checking");

        boolean result = menu.closeAccount(account);
        

        assertFalse(result, "Account with nonzero balance should not be closed.");
        assertTrue(customer.getAccounts().contains(account), "Account should still exist in the customer’s account list.");
    }

    @Test
    void testCloseNonExistentAccount() {
		String input = "1\n";
	    InputStream originalIn = System.in;
	    InputStream testIn = new ByteArrayInputStream(input.getBytes());
	    System.setIn(testIn);
	    
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account = new BankAccount(0.0, "checking"); // This account is NOT added to the customer

        boolean result = menu.closeAccount(account);

        assertFalse(result, "Closing an account that doesn't exist should return false.");

        System.setIn(originalIn);
    }
    
    @Test
    public void testFindAccount() {
		String input = "1\n";
	    InputStream originalIn = System.in;
	    InputStream testIn = new ByteArrayInputStream(input.getBytes());
	    System.setIn(testIn);
	    
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account = new BankAccount(0.0, "checking"); // This account is NOT added to the customer
        
		PrintStream originalOut = System.out;
		ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
		
        menu.findAccount();
       
        String output = testOut.toString();
        assertTrue(output.contains("No checking accounts available."), "Output should indicate that no checking accounts are available");
        
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    public void testSuccessfulTransfer() {
    	Customer customer = new Customer("Lila", "126");
    	Menu menu = new Menu(customer); 
        BankAccount account1 = menu.openAccount(150.0, "checking");
        BankAccount account2 = menu.openAccount(500.0, "checking");
        
        double transferAmount = 100.0;


        menu.transfer(account1, account2, transferAmount);

        
        assertEquals(50.0, account1.getCurrentBalance(), 0.001, "Account1 balance should be $50.0 after transfer");
        assertEquals(600.0, account2.getCurrentBalance(), 0.001, "Account2 balance should be $600.0 after transfer");
    }
    
   
}

