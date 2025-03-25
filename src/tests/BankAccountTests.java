package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import exceptions.InsufficientFundsException;

public class BankAccountTests {

	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
		
		//2. Call the method being tested
		account.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testZeroDeposit() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 0, 0.005);
		}
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		BankAccount account = new BankAccount();

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 0, 0.005);
		}
	}
	
	@Test
	public void testSimpleWithdrawl() {
		BankAccount account = new BankAccount();
		account.deposit(20);
		account.withdraw(5);
		assertEquals(account.getCurrentBalance(), 15.0, 0.005);
	}
	
	@Test
	public void testExactWithdrawl() {
		BankAccount account = new BankAccount();
		account.deposit(20);
		account.withdraw(20);
		assertEquals(account.getCurrentBalance(), 0, 0.005);
	}
	
	@Test
	public void testZeroWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
	
	@Test
	public void testNegativeWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(-5);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
	
	@Test
	public void testOverdraftWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(25);
			fail();
		} catch (InsufficientFundsException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
	
	@Test
	public void testSimpleWithdrawl() {
		BankAccount account = new BankAccount();
		account.deposit(20);
		account.withdraw(5);
		assertEquals(account.getCurrentBalance(), 15.0, 0.005);
	}
	
	@Test
	public void testExactWithdrawl() {
		BankAccount account = new BankAccount();
		account.deposit(20);
		account.withdraw(20);
		assertEquals(account.getCurrentBalance(), 0, 0.005);
	}
	
	@Test
	public void testZeroWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
	
	@Test
	public void testNegativeWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(-5);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
	
	@Test
	public void testOverdraftWithdrawl() {
		BankAccount account = new BankAccount();

		try {
			account.deposit(20);
			account.withdraw(25);
			fail();
		} catch (InsufficientFundsException e) {
			assertTrue(e != null);
			assertEquals(account.getCurrentBalance(), 20.0, 0.005);
		}
	}
}
