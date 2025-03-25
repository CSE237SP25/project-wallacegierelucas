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


}
