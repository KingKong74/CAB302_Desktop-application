import com.example.main_sem_proj.controller.MainController;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private MainController controller;

    @BeforeEach
    void setUp() {
        controller = new MainController(); // Initialize
    }

    @Test
    void testHandleSwitchClick_DARK_MODE() {
        controller.handleSwitchClick(MainController.Setting.DARK_MODE);
        assertEquals(MainController.Setting.DARK_MODE, controller.getCurrentSetting());
    }

    @Test
    void testHandleSwitchClick_FOCUS_MODE() {
        controller.handleSwitchClick(MainController.Setting.FOCUS_MODE);
        assertEquals(MainController.Setting.FOCUS_MODE, controller.getCurrentSetting());
    }

    @Test
    void testHandleSwitchClick_DARK_MODE_twice() {
        controller.handleSwitchClick(MainController.Setting.DARK_MODE);
        controller.handleSwitchClick(MainController.Setting.DARK_MODE);
        assertNull(controller.getCurrentSetting()); // Because it should toggle back to null after the second click
    }

    @Test
    void testHandleSwitchClick_FOCUS_MODE_twice() {
        controller.handleSwitchClick(MainController.Setting.FOCUS_MODE);
        controller.handleSwitchClick(MainController.Setting.FOCUS_MODE);
        assertNull(controller.getCurrentSetting());
    }

    @Test
    void testSignout() {

    }
}
