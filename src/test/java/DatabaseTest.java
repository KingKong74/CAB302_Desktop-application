import com.example.main_sem_proj.model.database.SqliteConnection;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @Test
    public void testConnection() {
        Connection conn = SqliteConnection.getInstance();
        assertEquals(true, conn != null);
    }
}
