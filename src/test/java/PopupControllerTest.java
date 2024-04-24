import com.example.main_sem_proj.controller.PopupController;
import com.example.main_sem_proj.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PopupControllerTest {

    // Pop-up window W x H
    private static final double WIDTH = 200;
    private static final double HEIGHT = 250;

    @Test
    void testPopupExceedsScreenHeight() {
        double screenWidth = 800;
        double screenHeight = 600;
        double popupX = 200;
        double popupY = 550; // This would cause the popup to exceed the screen height

        double[] adjusted = PopupController.adjustPopupPosition(popupX, popupY, screenWidth, screenHeight);
        assertArrayEquals(new double[]{200, screenHeight - HEIGHT}, adjusted, "Popup should adjust upward " +
                "to fit on screen");
    }

    @Test
    void testPopupExceedsScreenWidth() {
        double screenWidth = 800;
        double screenHeight = 600;
        double popupX = 700; // This would cause the popup to exceed the screen width
        double popupY = 200;

        double[] adjusted = PopupController.adjustPopupPosition(popupX, popupY, screenWidth, screenHeight);
        assertArrayEquals(new double[]{screenWidth - WIDTH, 200}, adjusted, "Popup should adjust to the " +
                "left to fit on screen");
    }

    @Test
    void testPopupWithinBounds() {
        double screenWidth = 800;
        double screenHeight = 600;
        double popupX = 300;
        double popupY = 200;

        double[] adjusted = PopupController.adjustPopupPosition(popupX, popupY, screenWidth, screenHeight);
        assertArrayEquals(new double[]{300, 200}, adjusted, "Popup should not adjust when within bounds");
    }

    @Test
    public void testLoadFXML() throws IOException {
        FXMLLoader fxmlLoader = PopupController.loadFXML("View");
        assertNotNull(fxmlLoader);
    }

}
