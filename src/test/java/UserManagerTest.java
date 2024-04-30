import com.example.main_sem_proj.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests will fail cause added first/last name to user details..
//
public class UserManagerTest {
    private UserManager userManager;
    private User[] users = {
            new User("JohnGreen@gmail.com", "Green123", "John", "Green"),
            new User("JayGreen@gmail.com", "Green1234", "Jay", "Green"),
            new User("SammyJane@gmail.com", "Jane123", "Sammy", "Jane"),
            new User("SammyJam@gmail.com", "Jam123", "Sammy", "Jam"),
            new User("JosephBarn@gmail.com", "Barn123","Joseph", "Barn")
    };
    private User[] EmptyUsers = {};

    @BeforeEach
    public void setUp() {
        userManager = new UserManager(new MockUserDAO());
    }
    @Test
    void testUserSearch_OneUser(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("JohnGreen@gmail.com");
        assertEquals("JohnGreen@gmail.com", users.get(0).getEmail());
    }
    @Test
    void testUserSearch_PasswordExists(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Barn123");
        assertEquals(1, users.size());
    }
    @Test
    void testUserSearch_FirstNameExists(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Sammy");
        assertEquals(2, users.size());
    }
    @Test
    void testUserSearch_LastNameExists(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Jam");
        assertEquals(1, users.size());
    }
    @Test
    public void testUserSearch_DuplicateNames(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Green");
        assertEquals(2, users.size());
        for (User user : users) {
            assertTrue(user.getLastName().equals("Green"));
        }
    }
    @Test
    void testUserSearch_FullName() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Joseph Barn");
        assertEquals(1, users.size());
        assertEquals("Joseph", users.get(0).getFirstName());
        assertEquals("Barn", users.get(0).getLastName());
    }
    @Test
    void testUserSearch_FullName_CaseInsensitive() {
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("joSEPh BARn");
        assertEquals(1, users.size());
        assertEquals("Joseph", users.get(0).getFirstName());
        assertEquals("Barn", users.get(0).getLastName());
    }
    @Test
    void testUserSearch_EmailExists_CaseInsensitive(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("joHNgrEEN@gmail.COM");
        assertEquals("JohnGreen@gmail.com", users.get(0).getEmail());
    }
    @Test
    void testUserSearch_EmailPasswordExists(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("SammyJane@gmail.com");
        List<User> usersPass = userManager.searchUsers("Jane123");
        assertEquals("SammyJane@gmail.com", users.get(0).getEmail());
        assertEquals("Jane123", users.get(0).getPassword());
    }
    @Test
    void testUserSearch_NoUserExists(){
        for (User user : users) {
            userManager.addUser(user);
        }
        List<User> users = userManager.searchUsers("Ben");
        assertEquals(0, users.size());
    }
    @Test
    void testUserSearch_EmptyUsers(){
        for (User user : EmptyUsers) {
            userManager.addUser(user);
        }
        List<User> EmptyUsers = userManager.searchUsers("JessicaSmith@gmail.com");
        assertEquals(0, EmptyUsers.size());
    }
}
