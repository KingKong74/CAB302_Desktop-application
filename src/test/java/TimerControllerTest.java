import com.example.main_sem_proj.controller.TimerController;
import org.junit.jupiter.api.*;
import javafx.animation.Timeline;
import static org.junit.jupiter.api.Assertions.*;

public class TimerControllerTest {

    private TimerController timerController;

    @BeforeEach
    public void setUp() {
        timerController = new TimerController(() -> {});
    }

    @Test
    public void testFormatTime() {
        assertEquals("00:00:00", timerController.formatTime(0));
        assertEquals("00:01:00", timerController.formatTime(60));
        assertEquals("01:00:00", timerController.formatTime(3600));
        assertEquals("01:01:01", timerController.formatTime(3661));
    }
}
