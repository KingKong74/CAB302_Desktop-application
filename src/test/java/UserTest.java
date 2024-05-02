import com.example.main_sem_proj.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    @BeforeEach
    public void setUp() {
        user = new User("DanSmith@gmail.com","Dan","Smith", "Password123");
    }
    @Test
    public void testGetEmail(){
        assertEquals("DanSmith@gmail.com", user.getEmail());
    }
    @Test
    public void testSetEmail() {
        user.setEmail("JohnMac@gmail.com");
        assertEquals("JohnMac@gmail.com", user.getEmail());
    }
    @Test
    public void testGetPassword(){
        assertEquals("Password123", user.getPassword());
    }
    @Test
    public void testSetPassword() {
        user.setPassword("asdf123");
        assertEquals("asdf123", user.getPassword());
    }
    @Test
    public void testGetFirstName(){
        assertEquals("Dan", user.getFirstName());
    }
    @Test
    public void testSetFirstName() {
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());
    }
    @Test
    public void testGetLastName(){
        assertEquals("Smith", user.getLastName());
    }
    @Test
    public void testSetLastName() {
        user.setLastName("Mac");
        assertEquals("Mac", user.getLastName());
    }
}
