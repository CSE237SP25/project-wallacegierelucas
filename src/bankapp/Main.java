package bankapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Hey, what's your name?");
        String name = userInput.nextLine();
        
        Customer customer = new Customer(name);
        
        Menu menu = new Menu(customer);
        
        boolean exitCommand = false; 
        
        while(!exitCommand)
        {
        	exitCommand = menu.displayMenuOptions();
        }
        System.out.println("You have exited the program. Have a good day!");
        userInput.close();
    }
}
