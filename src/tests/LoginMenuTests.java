package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.LoginMenu;

public class LoginMenuTests {
	
	@BeforeEach
	public void clearUserFile() {
		try {
			FileWriter writer = new FileWriter("users.txt", false);
			writer.write("");
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
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
}
