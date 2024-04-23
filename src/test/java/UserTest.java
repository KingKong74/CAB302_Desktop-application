import com.example.main_sem_proj.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private UserDetails user;
    @BeforeEach
    public void setUp() {
        user = new UserDetails("Bubbles@gmail.com", "Password123");
    }
    @Test
    public void testGetEmail(){
        assertEquals("Bubbles@gmail.com", user.getEmail());
    }
    @Test
    public void testSetEmail() {
        user.setEmail("Bubbles@gmail.com");
        assertEquals("Bubbles@gmail.com", user.getEmail());
    }
    @Test
    public void testGetPassword(){
        assertEquals("Password123", user.getPassword());
    }
    @Test
    public void testSetPassword() {
        user.setPassword("Password123");
        assertEquals("Password123", user.getPassword());
    }
}
