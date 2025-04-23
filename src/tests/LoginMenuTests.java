package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

import org.junit.jupiter.api.*;

import bankapp.LoginMenu;

public class LoginMenuTests {

    private final String USERS_FILE = "users.txt";

    @BeforeEach
    public void clearUserFile() throws IOException {
        Files.write(Paths.get(USERS_FILE), new byte[0]);
    }

    @Test
    public void testRegister() {
        String input = String.join("\n",
                "test_user",        
                "pw123",            
                "pet's name?",     
                "Fluffy",           
                "yes"               
        ) + "\n";

        provideInput(input);

        LoginMenu loginMenu = new LoginMenu();
        String username = loginMenu.register();

        assertEquals("test_user", username);
        assertTrue(userExistsInFile("test_user"));
    }

    @Test
    public void testUserExists() {
        String input = String.join("\n",
                "test_user",        
                "pw123",            
                "pet's name?",     
                "Fluffy",           
                "yes"               
        ) + "\n";

        provideInput(input);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.register();

        assertTrue(loginMenu.userExists("test_user"));
        assertFalse(loginMenu.userExists("nonexistent_user"));
    }

    @Test
    public void testValidLogIn() {
        String input = String.join("\n",
                "test_user",        
                "pw123",            
                "pet's name?",      
                "Fluffy",           
                "yes",              
                "test_user",        
                "pw123"             
        ) + "\n";

        provideInput(input);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.register();
        String username = loginMenu.logIn();

        assertEquals("test_user", username);
    }

    @Test
    public void testCheckCredentials() {
        String input = String.join("\n",
                "test_user",      
                "pw123",           
                "pet's name?",      
                "Fluffy",           
                "yes"               
        ) + "\n";

        provideInput(input);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.register();

        assertTrue(loginMenu.checkCredentials("test_user", "pw123"));
        assertFalse(loginMenu.checkCredentials("test_user", "wrongpw"));
        assertFalse(loginMenu.checkCredentials("nonexistent_user", "pw123"));
    }

    @Test
    public void testResetPassword() {
        String input = String.join("\n",
                "test_user",        
                "pw123",            
                "pet's name?",      
                "Fluffy",           
                "yes",              
                "test_user",        
                "Fluffy",           
                "newpw123"          
        ) + "\n";

        provideInput(input);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.register();
        loginMenu.resetPassword();

        assertTrue(loginMenu.checkCredentials("test_user", "newpw123"));
        assertFalse(loginMenu.checkCredentials("test_user", "pw123"));
    }

    // Helper methods

    private void provideInput(String data) {
        InputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    private boolean userExistsInFile(String username) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(username + ",")) {
                    return true;
                }
            }
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
        return false;
    }
}
