import com.example.main_sem_proj.controller.PopupController;
import com.example.main_sem_proj.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PopupControllerTest {
   private int WIDTH = 100;
   private int HEIGHT = 200;

    @BeforeEach
    public void mockButtonData() {
        int WIDTH = 100;
        int HEIGHT = 200;
    }

    public void mockCreateScene(){

    }

    @Test
    public void testLoadFXML() throws IOException {
            FXMLLoader fxmlLoader = PopupController.loadFXML("View");
            assertNotNull(fxmlLoader);
    }

    @Test
    public void testCalculatePopupPosition() {
        double[] position = PopupController.calculatePopupPosition(100.0, 100.0);
        assertNotNull(position);
    }

}
