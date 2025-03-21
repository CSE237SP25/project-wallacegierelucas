package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;
import exceptions.InvalidMenuOptionException;

public class MenuTests {
	
	@Test
	public void testProcessDeposit() {
		Menu menu = new Menu();
		
		menu.processDeposit(20);
		
		BankAccount account = menu.getAccount();
		assertEquals(account.getCurrentBalance(), 20.0, 0.005);
	}
	
	@Test
	public void testProcessWithdrawal() {
		Menu menu = new Menu();
		
		menu.processDeposit(50);
		menu.processWithdrawal(20);
		
		BankAccount account = menu.getAccount();
		assertEquals(account.getCurrentBalance(), 30.0, 0.005);
	}
	
	@Test
	public void testInvalidMenuOption() {
		Menu menu = new Menu();
		
		try {
			menu.processUserOptionInput(4);
			fail();
		} catch (InvalidMenuOptionException e) {
			assertTrue(e != null);
		}
	}
}
