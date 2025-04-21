package bankapp;

public class Main {
	public static void main(String[] args) {
		LoginMenu loginMenu = new LoginMenu();

		String name = loginMenu.getUser();

		Customer customer = new Customer(name);
		
		AccountActivity activity = new AccountActivity();

		Menu menu = new Menu(customer, activity);

		boolean exitCommand = false; 

		while(!exitCommand){
			exitCommand = menu.run();
		}
		System.out.println("You have exited the program. Have a good day!");
	}
}
