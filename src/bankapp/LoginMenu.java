package bankapp;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import exceptions.InvalidMenuOptionException;

public class LoginMenu {
	private static final String LOGIN_FILE = "users.txt";

	private Scanner userInput;

	public LoginMenu() {
		this.userInput = new Scanner(System.in);
	}

	public String getUser() {
		boolean success = false;

		while(!success) {
			try {
				displayOptions();

				int optionInput = getUserOptionInput();
				String username = processUserInput(optionInput);

				success = true;

				return username;

			}
			catch(IllegalArgumentException e) {
				System.out.println("Attempt failed: " + e.getMessage());
			}	
			catch(InvalidMenuOptionException e) {
				System.out.println("Attempt failed: " + e.getMessage());
			}
		}

		return null;
	}

	public void displayOptions() {
		System.out.println("Welcome!");
		System.out.println("1. Register");
		System.out.println("2. Log In");
		System.out.println("Choose an option (1 or 2): ");
	}

	public int getUserOptionInput() {
		int option = userInput.nextInt();

		userInput.nextLine();

		return option;
	}

	private String processUserInput(int option) {
		if (option == 1) {
			return register();
		} 
		else if(option == 2) {
			return logIn();
		}
		else {
			throw new InvalidMenuOptionException("Must enter a valid menu option.");
		}
	}

	public String register() {
		System.out.println("Choose a username: ");
		String username = userInput.nextLine();

		if (userExists(username)) {
			throw new IllegalArgumentException("Username already exists.");
		}

		System.out.println("Choose a password: ");
		String password = userInput.nextLine();
		
		System.out.println("You entered:");
	    System.out.println("  Username: " + username);
	    System.out.println("  Password: " + password);
	    System.out.print("Does this look correct? (yes/no): ");
	    String confirmLogIn = userInput.nextLine().trim();
	    
	    if (!confirmLogIn.equalsIgnoreCase("yes")) {
	        System.out.println("Okay, let’s start over.\n");
	        return register();            
	    }
	    

		try {
			FileWriter fileWriter = new FileWriter(LOGIN_FILE, true);
			fileWriter.write(username + "," + password + "\n");
			System.out.println("User registered successfully.");
			fileWriter.close();
			return username;
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}

	public String logIn() {
		System.out.println("Enter username: ");
		String username = userInput.nextLine();

		System.out.println("Enter password: ");
		String password = userInput.nextLine();

		if(checkCredentials(username, password)) {
			System.out.println("Log in successful!");
			return username;
		}
		else {
			throw new IllegalArgumentException("Invalid username or password.");
		}
	}

	public boolean userExists(String username) {
		try {
			Scanner fileReader = new Scanner(new File(LOGIN_FILE));

			while(fileReader.hasNextLine()) {
				String[] loginInfo = fileReader.nextLine().split(",");

				if (loginInfo.length > 0 && loginInfo[0].equals(username)) {
					return true;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return false;
	}

	public boolean checkCredentials(String username, String password) {
		try {
			Scanner fileReader = new Scanner(new File(LOGIN_FILE));

			while(fileReader.hasNextLine()) {
				String[] loginInfo = fileReader.nextLine().split(",");

				if (loginInfo.length > 0 && loginInfo[0].equals(username) && loginInfo[1].equals(password)) {
					return true;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return false;
	}
}
