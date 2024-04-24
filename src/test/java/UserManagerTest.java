import com.example.main_sem_proj.model.MockUserDAO;
import com.example.main_sem_proj.model.UserDetails;
import com.example.main_sem_proj.model.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserManagerTest {
    private UserManager userManager;
    private UserDetails[] users = {
            new UserDetails("Strawberry@gmail.com", "password010"),
            new UserDetails("Blueberry@gmail.com", "Rain123"),
            new UserDetails("Banana@gmail.com", "Diamond777")
    };
    private UserDetails[] EmptyUsers = {};

    @BeforeEach
    void setUp() {
        userManager = new UserManager(new MockUserDAO());
    }

    @Test
    void testUserSearch_EmailExists(){
        for (UserDetails user : users) {
            userManager.addUser(user);
        }
        List<UserDetails> users = userManager.searchUsers("Strawberry@gmail.com");
        assertEquals("Strawberry@gmail.com", users.get(0).getEmail());
    }
    @Test
    void testUserSearch_EmailExists_CaseInsensitive(){
        for (UserDetails user : users) {
            userManager.addUser(user);
        }
        List<UserDetails> users = userManager.searchUsers("StrAWbeRrY@gmail.com");
        assertEquals("Strawberry@gmail.com", users.get(0).getEmail());
    }
    @Test
    void testUserSearch_EmailPasswordExists(){
        for (UserDetails user : users) {
            userManager.addUser(user);
        }
        List<UserDetails> users = userManager.searchUsers("Strawberry@gmail.com");
        List<UserDetails> usersPass = userManager.searchUsers("password010");
        assertEquals("Strawberry@gmail.com", users.get(0).getEmail());
        assertEquals("password010", users.get(0).getPassword());
    }
    @Test
    void testUserSearch_NoUsers(){
        for (UserDetails user : EmptyUsers) {
            userManager.addUser(user);
        }
        List<UserDetails> EmptyUsers = userManager.searchUsers("JessicaSmith@gmail.com");
        assertEquals(0, EmptyUsers.size());
    }
}
