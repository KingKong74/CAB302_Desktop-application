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


}
