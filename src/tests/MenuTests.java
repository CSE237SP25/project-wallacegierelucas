package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

<<<<<<< HEAD
		Customer newCustomer = new Customer("Lila");
		Menu newMenu = new Menu(newCustomer);
		BankAccount account = newMenu.openAccount();
		
		System.out.println(newCustomer.getAccounts().toString()); 

		System.setIn(originalInputStream);
=======

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
>>>>>>> c8b286ae0778b972cab8956d765851160fd9f906

		assertNotNull(account);
		assertEquals(500, account.getCurrentBalance(), 0.01);
		assertEquals(1, newCustomer.getAccounts().size());
		assertTrue(newCustomer.getAccounts().contains(account));
	}

	@Test
	public void testFindAccount() {
		String input = "1\n12345\n500\n"; 
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		Customer newCustomer = new Customer("Lila");
		Menu newMenu = new Menu(newCustomer);
		BankAccount account = newMenu.openAccount();
		
		System.out.println(newCustomer.getAccounts().toString()); 

		System.setIn(originalInputStream);
		
		BankAccount found = newMenu.findAccount("12345");
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
		InputStream originalIn = System.in;
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount();

		System.setIn(originalIn);

		assertTrue(result);
		assertFalse(customer.getAccounts().contains(account));
	}

	@Test
	public void testCloseAccountWithNonZeroBalance() {
		String input = "1\nacc123\n500\n"; 
		InputStream originalIn = System.in;
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount(); 

		System.setIn(originalIn);

		assertFalse(result);
		assertTrue(customer.getAccounts().contains(account));
	}

	@Test
	public void testCloseNonExistentAccount() {
		String input = "1\nnonexistent\n"; 
		InputStream originalIn = System.in; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		customer.addAccount(new BankAccount(100, "savings", "acc123"));

		boolean result = menu.closeAccount();
		System.setIn(originalIn);
		assertFalse(result, "Closing an account that doesn't exist should return false.");
	}

	@Test
	public void testSuccessfulTransfer() throws InsufficientFundsException {
		BankAccount account1 = new BankAccount(500, "checking", "acc1");
		BankAccount account2 = new BankAccount(300, "checking", "acc2");

		customer.addAccount(account1);
		customer.addAccount(account2);

		String input = "acc1\nacc2\n100\n"; 
		InputStream originalIn = System.in;
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		menu.transfer();

		System.setIn(originalIn);

		assertEquals(400, account1.getCurrentBalance(), 0.001);
		assertEquals(400, account2.getCurrentBalance(), 0.001);
	}

	//@Test
	//public void testTransferInsufficientFunds() {

	//Customer customer = new Customer("Lila");
	//	Menu menu = new Menu(customer);

	//	BankAccount account1 = new BankAccount(50, "checking", "acc1"); 
	//BankAccount account2 = new BankAccount(300, "checking", "acc2");

	//	customer.addAccount(account1);
	//	customer.addAccount(account2);

	//	String input = "acc1\nacc2\n100\n"; 
	//	InputStream originalInputStream = System.in;
	//	InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
	//	System.setIn(testInputStream);

	//	PrintStream originalOutputStream = System.out;
	//	System.setOut(new PrintStream(new ByteArrayOutputStream()));

	//	menu.transfer();

	//	System.setIn(originalInputStream);
	//	System.setOut(originalOutputStream);

	//	assertTrue(output.contains("Transfer failed due to insufficient funds."));
	//	assertEquals(50, account1.getCurrentBalance());
	//	assertEquals(300, account2.getCurrentBalance());


	//}

	@Test
	public void testTransferInsufficientFunds() {
		String input = "1\n123456\n50\n1\n1234567\n300\n123456\n1234567\n100\n";
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		Customer newCustomer = new Customer("Erika");
		Menu newMenu = new Menu(newCustomer);
		BankAccount account1 = newMenu.openAccount();
		BankAccount account2 = newMenu.openAccount();
		
		try {
			newMenu.transfer(); 
			fail();
		} catch (InsufficientFundsException e) {
			assertTrue(e != null, "Assertion1");
			assertEquals(50, account1.getCurrentBalance(), 0.001, "Assertion2");
			assertEquals(300, account2.getCurrentBalance(), 0.001, "Assertion3");
		}
	
		
		System.setIn(originalInputStream);
	}
}

