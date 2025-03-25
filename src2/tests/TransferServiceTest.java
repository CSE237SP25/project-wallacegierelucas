package tests;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.TransferService;

import static org.junit.jupiter.api.Assertions.*;

public class TransferServiceTest {


    @Test
    public void testSuccessfulTransfer() {
        BankAccount account1 = new BankAccount("111", 800.0);
        BankAccount account2 = new BankAccount("222", 500.0);
        double transferAmount = 100.0;


        TransferService.transfer(account1, account2, transferAmount);

        
        assertEquals(700.0, account1.getCurrentBalance(), 0.001, "Account1 balance should be $700.0 after transfer");
        assertEquals(800.0, account2.getCurrentBalance(), 0.001, "Account2 balance should be $600.0 after transfer");
    }

    @Test
    public void testInsufficientFunds() {
        
        BankAccount account1 = new BankAccount("111", 50.0);
        BankAccount account2 = new BankAccount("222", 400.0);
        double transferAmount = 300.0;

        TransferService.transfer(account1, account2, transferAmount);

        assertEquals(100.0, account1.getCurrentBalance(), 0.001, "Account1 balance should remain unchanged");
        assertEquals(500.0, account2.getCurrentBalance(), 0.001, "Account2 balance should remain unchanged");
    }

    @Test
    public void testNegativeTransferAmount() {
        
        BankAccount account1 = new BankAccount("111", 800.0);
        BankAccount account2 = new BankAccount("222", 400.0);
        double transferAmount = -100.0;

        
        TransferService.transfer(account1, account2, transferAmount);

        
        assertEquals(1000.0, account1.getCurrentBalance(), 0.001, "Account1 balance should remain unchanged with negative transfer");
        assertEquals(500.0, account2.getCurrentBalance(), 0.001, "Account2 balance should remain unchanged with negative transfer");
    }
}

