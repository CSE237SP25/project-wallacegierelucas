package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.BankAccountMenu;
import exceptions.InvalidMenuOptionException;

public class BankAccountMenuTests {
	
	@Test
	public void testUserDeposit() {
		BankAccountMenu menu = new BankAccountMenu();
		
		menu.processDeposit(20);
		
		BankAccount account = menu.getAccount();
		assertEquals(account.getCurrentBalance(), 20.0, 0.005);
	}
	
	@Test
	public void testUserWithdrawal() {
		BankAccountMenu menu = new BankAccountMenu();
		
		menu.processDeposit(50);
		menu.processWithdrawal(20);
		
		BankAccount account = menu.getAccount();
		assertEquals(account.getCurrentBalance(), 30.0, 0.005);
	}
	
	@Test
	public void testUserCheckBalance() {
		PrintStream originalOutputStream = System.out;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		
		BankAccountMenu menu = new BankAccountMenu();
		menu.processDeposit(50);
		menu.processCheckBalance();
		
		System.setOut(originalOutputStream);
		
		
		assertEquals("Current Balance: 50.0", outputStream.toString().trim());
	}
	
	@Test
	void testProcessUserOptionInput_Deposit() {		
		String input = "20\n";
	    
	    InputStream originalInputStream = System.in;
	    InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
	    System.setIn(testInputStream);
	    
	    // mutes the print statement in processUserOptionInput()
		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		
		BankAccountMenu menu = new BankAccountMenu();
		menu.processUserOptionInput(2);
		
		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);

		BankAccount account = menu.getAccount();
	    assertEquals(20.0, account.getCurrentBalance(), 0.005);
	}
	
	@Test
	void testProcessUserOptionInput_Withdrawal() {
	    String input = "20\n";
	    
	    InputStream originalInputStream = System.in;
	    InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
	    System.setIn(testInputStream);
	    
	    // mutes the print statement in processUserOptionInput()
		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		
		BankAccountMenu menu = new BankAccountMenu();
		menu.processDeposit(50);
		menu.processUserOptionInput(3);

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);
		
		BankAccount account = menu.getAccount();
	    assertEquals(30.0, account.getCurrentBalance(), 0.005);
	}
	
	@Test
	public void testInvalidMenuOption() {
		BankAccountMenu menu = new BankAccountMenu();
		
		try {
			menu.processUserOptionInput(4);
			fail();
		} catch (InvalidMenuOptionException e) {
			assertTrue(e != null);
		}
	}
}
