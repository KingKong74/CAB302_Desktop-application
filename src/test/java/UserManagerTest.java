import com.example.main_sem_proj.model.users.MockUserDAO;
import com.example.main_sem_proj.model.users.User;
import com.example.main_sem_proj.model.users.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {
    private UserManager userManager;
    private User[] users = {
            new User("JohnGreen@gmail.com","John", "Green", "Green123"),
            new User("JayGreen@gmail.com", "Jay", "Green", "Green1234"),
            new User("SammyJane@gmail.com", "Sammy", "Jane", "Jane123"),
            new User("SammyJam@gmail.com", "Sammy", "Jam", "Jam123"),
            new User("JosephBarn@gmail.com","Joseph", "Barn", "Barn123")
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
