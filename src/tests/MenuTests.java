package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import bankapp.BankAccount;
import bankapp.Customer;
import bankapp.Menu;
import exceptions.InsufficientFundsException;

import java.io.*;
import java.util.List;

public class MenuTests {
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;
	private ByteArrayOutputStream testOut;
	private Customer customer = new Customer("Erika"); 
	private Menu menu = new Menu(customer);

	@Test
	public void testInvalidSelection() {
		String input = "3\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		menu.findAccount("acc123");
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			menu.openAccount();
		});
		assertEquals("Invalid selection", exception.getMessage());
	}
	

	@Test
	public void testOpenAccount() {
		
		String input = "1\n12345\n500\n"; 
	    InputStream originalInputStream = System.in;
	    InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
	    System.setIn(testInputStream);
	    
		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		Customer customer = new Customer("Lila");
		Menu menu = new Menu(customer);
		BankAccount account = menu.openAccount();

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);

		assertNotNull(account);
		assertEquals(500, account.getCurrentBalance(), 0.01);
		assertEquals(1, customer.getAccounts().size());
		assertTrue(customer.getAccounts().contains(account));
	}
	
	@Test
	public void testFindAccount() {
		BankAccount account = new BankAccount(500, "checking", "acc123");
		customer.addAccount(account);

		BankAccount found = menu.findAccount("acc123");
		assertEquals(account, found, "The correct account should be found");
	}
	
	@Test
	public void testFindNonExistentAccount() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			menu.findAccount("nonexistent");
		});
		assertEquals("Invalid account id.", exception.getMessage());
	}
	
	@Test
	public void testCloseAccountWithZeroBalance() {
		String input = "1\nacc123\n0\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount();

		assertTrue(result);
		assertFalse(customer.getAccounts().contains(account));
	}
	
	@Test
	public void testCloseAccountWithNonZeroBalance() {
		String input = "1\nacc123\n500\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount(); 

		assertFalse(result);
		assertTrue(customer.getAccounts().contains(account));
	}
	
	@Test
	public void testCloseNonExistentAccount() {
		String input = "1\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		boolean result = menu.closeAccount();
		assertFalse(result, "Closing an account that doesn't exist should return false.");
	}
	
	@Test
	public void testSuccessfulTransfer() throws InsufficientFundsException {
		BankAccount account1 = new BankAccount(500, "checking", "acc1");
		BankAccount account2 = new BankAccount(300, "checking", "acc2");
		customer.addAccount(account1);
		customer.addAccount(account2);

		String input = "acc1\nacc2\n100\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		menu.transfer();

		assertEquals(400, account1.getCurrentBalance(), 0.001);
		assertEquals(400, account2.getCurrentBalance(), 0.001);
	}
	
	@Test
	public void testTransferInsufficientFunds() {
		BankAccount account1 = new BankAccount(50, "checking", "acc1"); 
		BankAccount account2 = new BankAccount(300, "checking", "acc2");
		customer.addAccount(account1);
		customer.addAccount(account2);

		String input = "acc1\nacc2\n100\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		menu.transfer();

		String output = testOut.toString();
		assertTrue(output.contains("Transfer failed due to insufficient funds."));
		assertEquals(50, account1.getCurrentBalance());
		assertEquals(300, account2.getCurrentBalance());
	}
}

