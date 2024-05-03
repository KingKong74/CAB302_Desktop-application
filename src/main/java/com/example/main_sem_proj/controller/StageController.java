package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {

    /**
     * Opens a new window with the specified title, width, and height, while closing the specified stage.
     *
     * @param title        The title of the new window.
     * @param width        The width of the new window.
     * @param height       The height of the new window.
     * @param prevStage The existing Stage object that needs to be closed when the new window opens.
     */
    public void openStage(FXMLLoader fxmlLoader, String title, int width, int height, Stage prevStage) {
        Stage newStage = createStage(fxmlLoader, title, width, height);
        newStage.show();
        closeStage(prevStage);
    }

    /**
     * Opens mainStage, while setting the position to the specified coordinates,
     * and closing the specified stage.
     *
     * @param title        The title of the new window.
     * @param width        The width of the new window.
     * @param height       The height of the new window.
     * @param prevStage    The existing Stage object that needs to be closed when the new window opens.
     * @param stageX       The x-coordinate of the stage position.
     * @param stageY       The y-coordinate of the stage position.
     */
    public void openStage(FXMLLoader fxmlLoader, String title, int width, int height, Stage prevStage, double stageX, double stageY) {
        Stage newStage = createStage(fxmlLoader, title, width, height);
        newStage.setX(stageX);
        newStage.setY(stageY);
        newStage.show();
        closeStage(prevStage);
    }

    public FXMLLoader loadFXML(String VIEW) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/" + VIEW + "-view.fxml"));
        fxmlLoader.load();
        return fxmlLoader;

    }

    public Stage createStage(FXMLLoader fxmlLoader, String title, int width, int height) {
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.getRoot(), width, height);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        return stage;
    }


    /**
     * Calculates the position of the bottom right corner of the screen for a stage with the given width and height.
     *
     * @param width The width of the stage.
     * @param height The height of the stage.
     * @return A Point2D object representing the bottom-right coordinates.
     */
    public Point2D calculateBottomRightPosition(double width, double height) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double bottomRightX = screenWidth - (width + 9);
        double bottomRightY = screenHeight - (height + 75);

        return new Point2D(bottomRightX, bottomRightY);
    }


    /**
     * Closes the specified stage.
     */
    public void closeStage(Stage stageToClose) {
        if (stageToClose != null) {
            stageToClose.close();
        }
    }
}