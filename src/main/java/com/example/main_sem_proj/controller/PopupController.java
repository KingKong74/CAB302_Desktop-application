package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupController extends MainController {
    private static final double WIDTH = 200;
    private static final double HEIGHT = 250;
    public static Stage popupStage;

    /**
     * Handles the button click to open or close the popup window.
     *
     * @param VIEW  The name of the FXML view file for the popup.
     * @param x     The x-coordinate of the mouse click event.
     * @param y     The y-coordinate of the mouse click event.
     * @param TITLE The title of the popup window.
     */
    @FXML
    public static void ButtonClick(String VIEW, double x, double y, String TITLE) {
        if (isPopupOpen) {
            closePopup();
            return;
        }
        try {
            FXMLLoader fxmlLoader = loadFXML(VIEW);
            Scene scene = createScene(fxmlLoader);

            double[] popupPosition = calculatePopupPosition(x, y);
            double popupX = popupPosition[0];
            double popupY = popupPosition[1];

            popupStage = createPopupStage(scene, popupX, popupY, TITLE);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the FXML file for the popup.
     *
     * @param view The name of the FXML view file.
     * @return FXMLLoader object for the loaded FXML file.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private static FXMLLoader loadFXML(String view) throws IOException {
        return new FXMLLoader(HelloApplication.class.getResource("view/" + view + "-view.fxml"));
    }

    /**
     * Creates the scene for the popup.
     *
     * @param fxmlLoader FXMLLoader object for the loaded FXML file.
     * @return Scene object for the popup.
     * @throws IOException If an error occurs while creating the scene.
     */
    private static Scene createScene(FXMLLoader fxmlLoader) throws IOException {
        return new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
    }

    /**
     * Calculates the position of the popup window based on the mouse click coordinates.
     *
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     * @return Array containing the x and y coordinates of the popup window.
     */
    private static double[] calculatePopupPosition(double x, double y) {
        double popupX = x - WIDTH / 2;
        double popupY = y - HEIGHT / 2 - 80;

        // Adjust if the popup goes out of the screen bounds
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
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
    private static void closePopup() {
        popupStage.close();
        isPopupOpen = false;
    }
}