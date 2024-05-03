package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {

    /**
     * Opens a new window with the specified view, title, width, and height, while closing the specified stage.
     *
     * @param view         The view to be displayed in the new window.
     * @param title        The title of the new window.
     * @param width        The width of the new window.
     * @param height       The height of the new window.
     * @param prevStage The existing Stage object that needs to be closed when the new window opens.
     */
    public static void openStage(String view, String title, int width, int height, Stage prevStage) {
        try {
            Stage newStage = createStage(view, title, width, height);
            newStage.show();
            closeStage(prevStage);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Creates a new stage with the specified view, title, width, and height.
     *
     * @param VIEW   The name of the FXML view file to load.
     * @param title  The title of the stage.
     * @param width  The width of the stage.
     * @param height The height of the stage.
     * @return The newly created stage.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    protected static Stage createStage(String VIEW, String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/" + VIEW + "-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        return stage;
    }


    /**
     * Closes the specified stage.
     */
    public static void closeStage(Stage stageToClose) {
        if (stageToClose != null) {
            stageToClose.close();
        }
    }
}