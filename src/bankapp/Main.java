package bankapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Hey, what's your name?");
        String name = userInput.nextLine();
        
        Customer customer = new Customer(name);
        
        Menu menu = new Menu(customer);
        
        int i = 0;
        while(i < 10) {
            menu.run();
            ++i;
        }
        
        userInput.close();
    }
}
