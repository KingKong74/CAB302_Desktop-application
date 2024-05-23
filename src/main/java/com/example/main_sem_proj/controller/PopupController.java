package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import com.example.main_sem_proj.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupController extends MainController {
    private static final double WIDTH = 270;
    private static final double HEIGHT = 350;
    public static Stage popupStage;

    /**
     * Handles the button click to open or close the popup window.
     *
     * @param view  The name of the FXML view file for the popup.
     * @param x     The x-coordinate of the mouse click event.
     * @param y     The y-coordinate of the mouse click event.
     * @param title The title of the popup window.
     */
    @FXML
    public static void handleOpenPopup(String view, double x, double y, String title) {
        if (isPopupOpen) {
            closePopup();
            return;
        }
        openPopup(view, x, y, title);
    }

    /**
     * Opens the popup window.
     *
     * @param view  The name of the FXML view file for the popup.
     * @param x     The x-coordinate of the mouse click event.
     * @param y     The y-coordinate of the mouse click event.
     * @param title The title of the popup window.
     */
    private static void openPopup(String view, double x, double y, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/" + view + "-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

            double[] screenParams = getScreenParams();
            double screenWidth = screenParams[0];
            double screenHeight = screenParams[1];

            double[] popupPosition = calculatePopupPosition(x, y, screenWidth, screenHeight);
            double popupX = popupPosition[0];
            double popupY = popupPosition[1];

            popupStage = createPopupStage(scene, popupX, popupY, title);
            popupStage.setResizable(false);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static double[] getScreenParams() {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        return new double [] {screenWidth, screenHeight};
    }

    /**
     * Calculates the position of the popup window based on the mouse click coordinates.
     *
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     * @return Array containing the x and y coordinates of the popup window.
     */

    public static double[] calculatePopupPosition(double x, double y, double screenWidth, double screenHeight) {
        double popupX = x - WIDTH / 2;
        double popupY = y - HEIGHT / 2 - 80;

        return adjustPopupPosition(popupX, popupY, screenWidth, screenHeight);
    }

    /**
     * Adjusts the position of the popup window if it goes out of the screen bounds.
     *
     * @param popupX       The calculated x-coordinate of the popup window.
     * @param popupY       The calculated y-coordinate of the popup window.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     * @return Array containing the adjusted x and y coordinates of the popup window.
     */
    public static double[] adjustPopupPosition(double popupX, double popupY, double screenWidth, double screenHeight) {
        if (popupX < 0) {
            popupX = 0;
        } else if (popupX + WIDTH > screenWidth) {
            popupX = screenWidth - WIDTH;
        }
        if (popupY < 0) {
            popupY = 0;
        } else if (popupY + HEIGHT > screenHeight) {
            popupY = screenHeight - HEIGHT;
        }

        return new double[]{popupX, popupY};
    }

    /**
     * Creates the stage for the popup window.
     *
     * @param scene   Scene object for the popup.
     * @param popupX  The x-coordinate of the popup window.
     * @param popupY  The y-coordinate of the popup window.
     * @param TITLE   The title of the popup window.
     * @return Stage object for the popup window.
     */
    private static Stage createPopupStage(Scene scene, double popupX, double popupY, String TITLE) {
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.setOnCloseRequest(event -> {
            System.out.println("Popup is closing");
            isPopupOpen = false;
        });
        popupStage.setTitle(TITLE);
        popupStage.setX(popupX);
        popupStage.setY(popupY);
        isPopupOpen = true;
        return popupStage;
    }

    /**
     * Closes the popup window.
     */
    public static void closePopup() {
        popupStage.close();
        isPopupOpen = false;
    }
}
