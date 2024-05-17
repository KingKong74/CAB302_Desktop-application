import com.example.main_sem_proj.controller.MainController;
import javafx.animation.Animation;
import org.junit.jupiter.api.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private final int notificationTime = 6;

    private MainController controller;

    @BeforeEach
    void setUp() {
        controller = new MainController(); // Initialize
    }

    @Test
    public void testFormatTime() {
        // Test cases for different input values
        assertEquals("00:00:00", controller.formatTime(0));  // Zero seconds
        assertEquals("00:00:30", controller.formatTime(30)); // 30 seconds
        assertEquals("00:01:00", controller.formatTime(60)); // 1 minute
        assertEquals("01:00:00", controller.formatTime(3600)); // 1 hour
        assertEquals("01:02:03", controller.formatTime(3723)); // 1 hour, 2 minutes, 3 seconds
    }

}