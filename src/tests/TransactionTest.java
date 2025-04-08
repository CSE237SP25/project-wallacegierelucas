package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import bankapp.BankAccount;
import bankapp.Customer;
import bankapp.Menu;
import exceptions.InsufficientFundsException;
import bankapp.Transaction;
import java.io.*;
import java.util.*;

public class TransactionTest {
//	@Test
//	public void testAddTransaction() {
//	    BankAccount account = new BankAccount(0, "checking", "acc1");
//
//	    account.deposit(200);
//	    account.withdraw(50);
//
//	    List<Transaction> transactions = account.getTransactions();
//
//	    assertEquals(2, transactions.size());
//
//	    Transaction first = transactions.get(0);
//	    assertEquals("deposit", first.getType());
//	    assertEquals(200, first.getAmount(), 0.01);
//
//	    Transaction second = transactions.get(1);
//	    assertEquals("withdrawal", second.getType());
//	    assertEquals(50, second.getAmount(), 0.01);
//	}
//	
//	@Test
//	public void testGetDepositsOnly() {
//	    BankAccount account = new BankAccount(0, "checking", "acc2");
//
//	    account.deposit(100);
//	    account.withdraw(20);
//	    account.deposit(50);
//
//	    List<Transaction> deposits = account.getTransactionsByType("deposit");
//
//	    assertEquals(2, deposits.size());
//	    for (Transaction t : deposits) {
//	        assertEquals("deposit", t.getType());
//	    }
//	}
//	@Test
//	public void testGetWithdrawalsOnly() throws InsufficientFundsException {
//	    BankAccount account = new BankAccount(1000, "checking", "acc3");
//
//	    account.withdraw(300);
//	    account.deposit(500);
//	    account.withdraw(100);
//
//	    List<Transaction> withdrawals = account.getTransactionsByType("withdrawal");
//
//	    assertEquals(2, withdrawals.size());
//	    for (Transaction t : withdrawals) {
//	        assertEquals("withdrawal", t.getType());
//	    }
//	}
//	@Test
//	public void testNoMatchingFilter() {
//	    BankAccount account = new BankAccount(0, "checking", "acc4");
//
//	    account.deposit(100);
//	    List<Transaction> result = account.getTransactionsByType("refund");
//
//	    assertTrue(result.isEmpty());
//	}

}
