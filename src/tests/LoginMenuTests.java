package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Customer;
import bankapp.LoginMenu;
import bankapp.Menu;

public class LoginMenuTests {

	@AfterEach
	public void clearUserFile() {
		try {
			FileWriter writer = new FileWriter("users.txt", false);
			writer.write("");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	public static void clearUserInfoFiles() {
	    File folder = new File("usersInfo");
	    if (folder.exists()) {
	        for (File file : folder.listFiles()) {
	            file.delete();
	        }
	    }
	}
	
	@Test
	public void testRegister() {			    	    
		String input = "test_user\npw123\n";

		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		LoginMenu loginMenu = new LoginMenu();
		String username = loginMenu.register();

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);

		assertEquals("test_user", username);
	}

	@Test
	public void testUserExists() {
		String input = "test_user\npw123\n";

		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		LoginMenu loginMenu = new LoginMenu();
		String username = loginMenu.register();

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);

		assertTrue(loginMenu.userExists(username));
		assertFalse(loginMenu.userExists("test_user123"));
	}

	@Test 
	public void testValidLogIn() {
		String input = "test_user\npw123\ntest_user\npw123\n";

		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		LoginMenu loginMenu = new LoginMenu();
		loginMenu.register();
		String username = loginMenu.logIn();

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);


		assertEquals("test_user", username);
	}

	@Test
	public void testCheckCredentials() {
		String input = "test_user\npw123\n";

		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);

		PrintStream originalOutputStream = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		LoginMenu loginMenu = new LoginMenu();
		loginMenu.register();

		System.setIn(originalInputStream);
		System.setOut(originalOutputStream);

		assertTrue(loginMenu.checkCredentials("test_user", "pw123"));
		assertFalse(loginMenu.checkCredentials("test", "pw123"));
		assertFalse(loginMenu.checkCredentials("test_user", "pw"));
	}
	
	@Test
	public void testStoreAndRetrieveCustomerInfo() {
		String input = "software engineer\n123 candy cane lane\n"+ "1\ncheck123\n50\n" + "2\nsave123\n500\n"; 
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);
		
		LoginMenu loginMenu = new LoginMenu();
		String username = "test_user";
		Customer customer = new Customer(username);
		
		Menu menu = new Menu(customer);
		menu.updateProfile();
		menu.openAccount();
		menu.openAccount();
		
		loginMenu.storeCustomerInfo(customer);
		
		Customer retrievedCustomer = loginMenu.retrieveCustomerInfo(username);
		
		System.setIn(originalInputStream);
		
		assertNotNull(retrievedCustomer);
        assertEquals(customer.toString(), retrievedCustomer.toString());
	}
	
	@Test
	public void testRetrieveNewCustomerInfo() {
		String input = "software engineer\n123 candy cane lane\n"+ "1\nsave123\n500\n"; 
		InputStream originalInputStream = System.in;
		InputStream testInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(testInputStream);
		
		LoginMenu loginMenu = new LoginMenu();
		String username = "test_user";
		Customer customer = new Customer(username);
		
		Menu menu = new Menu(customer);
		menu.updateProfile();
		menu.openAccount();
		
		Customer retrievedCustomer = loginMenu.retrieveCustomerInfo(username);
		
		System.setIn(originalInputStream);

		assertNotNull(retrievedCustomer);
		assertEquals(customer.getName(), retrievedCustomer.getName());
        assertNotEquals(customer.toString(), retrievedCustomer.toString());
	}
}
