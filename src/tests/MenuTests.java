package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import bankapp.BankAccount;
import bankapp.Customer;
import bankapp.Menu;
import bankapp.Transaction;
import bankapp.AccountActivity; 
import exceptions.InsufficientFundsException;
import exceptions.InvalidMenuOptionException;

import java.io.*;
import java.util.List;

public class MenuTests {
	private Customer customer = new Customer("Erika"); 
	private AccountActivity activity = new AccountActivity();
	private Menu menu = new Menu(customer,activity);

	@Test
	public void testInvalidSelection() {
		String input = "12\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Customer customer = new Customer("Lila");
		Menu menu = new Menu(customer,activity);

		Exception exception = assertThrows(InvalidMenuOptionException.class, () -> {
			menu.getMenuOptionInput();
		});
		assertEquals("Invalid menu option.", exception.getMessage());
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
		Menu menu = new Menu(customer,activity);
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
		String input = "1\n12345\n500\n"; 
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		Customer newCustomer = new Customer("Lila");
		Menu menu = new Menu(newCustomer,activity);
		BankAccount account = menu.openAccount();

		System.setIn(originalInputStream);

		BankAccount found = menu.findAccount("12345");
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
		String input = "1\nacc123\n0\n" + "1\nacc123\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Customer newCustomer = new Customer("Lila");
		Menu menu = new Menu(newCustomer,activity);
		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount();

		assertTrue(result);
		assertFalse(customer.getAccounts().contains(account));
	}

	@Test
	public void testCloseAccountWithNonZeroBalance() {
		String input = "1\nacc123\n500\n" + "1\nacc123\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Customer newCustomer = new Customer("Lila");
		Menu menu = new Menu(newCustomer,activity);
		BankAccount account = menu.openAccount();
		boolean result = menu.closeAccount();

		assertFalse(result);
		assertTrue(newCustomer.getAccounts().contains(account));
	}

	@Test
	public void testCloseNonExistentAccount() {
		String input = "1\n"; 
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Customer newCustomer = new Customer("Lila");
		Menu menu = new Menu(newCustomer,activity);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			menu.closeAccount();
		});
		assertEquals("No accounts to manage.", exception.getMessage());
	}

	@Test
	public void testSuccessfulTransfer() throws InsufficientFundsException {
		String input = "1\nacct1\n50\n" + "1\nacct2\n300\n" + "acct1\nacct2\n25\n";
		InputStream originalInputStream = System.in;
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		Customer newCustomer = new Customer("Erika");
		Menu newMenu = new Menu(newCustomer,activity);
		BankAccount account1 = newMenu.openAccount();
		BankAccount account2 = newMenu.openAccount();

		newMenu.transfer();

		System.setIn(originalInputStream);

		assertEquals(25, account1.getCurrentBalance(), 0.001);
		assertEquals(325, account2.getCurrentBalance(), 0.001);
	}

	@Test
	public void testTransferInsufficientFunds() {
		String input = "1\nacct1\n50\n" + "1\nacct2\n300\n" + "acct1\nacct2\n100\n";
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		Customer newCustomer = new Customer("Erika");
		Menu newMenu = new Menu(newCustomer,activity);
		BankAccount account1 = newMenu.openAccount();
		BankAccount account2 = newMenu.openAccount();

		try {
			newMenu.transfer(); 
			fail();
		} catch (InsufficientFundsException e) {
			assertTrue(e !=  null, "Assertion1");
			assertEquals(50, account1.getCurrentBalance(), 0.001, "Assertion2");
			assertEquals(300, account2.getCurrentBalance(), 0.001, "Assertion3");
		}

		System.setIn(originalInputStream);
	}
	 @Test
	    public void testFreezeAccount() {
	        AccountActivity activity = new AccountActivity();
	        BankAccount account = new BankAccount(0, "checking", "account11");
	        activity.addAccount(account);

	        activity.freezeAccount("account11");

	        activity.deposit("account11", 100);
	        assertEquals(0, account.getCurrentBalance(), 0.001, "Frozen account should not accept deposits");

	        List<Transaction> transactionReview = account.getTransactions();
	        assertFalse(transactionReview.isEmpty(), "Transactions should not be empty after freezing");
	        Transaction last = transactionReview.get(transactionReview.size() - 1);
	        assertEquals("Freeze", last.getType(), "Last transaction type should be 'Freeze'");
	        assertEquals(0, last.getAmount(), 0.001, "Freeze transaction amount should be 0");
	    }

	    @Test
	    public void testUnfreezeAccount() {
	    	 AccountActivity activity = new AccountActivity();
	         BankAccount account = new BankAccount(0, "checking", "account1");
	         activity.addAccount(account);

	         activity.freezeAccount("account1");
	         activity.unfreezeAccount("account1");

	         List<Transaction> transactionReview = account.getTransactions();
	         assertTrue(
	        		 transactionReview.stream().anyMatch(t -> "Unfreeze".equals(t.getType())),
	             "Transactions should include an 'Unfreeze' record"
	         );

	         activity.deposit("account1", 100);
	         assertEquals(100, account.getCurrentBalance(), 0.001, "Unfrozen account should accept deposits");
	    }
}

