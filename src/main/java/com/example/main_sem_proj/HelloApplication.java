package com.example.main_sem_proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import java.io.IOException;

public class HelloApplication extends Application {

    // Constants for width and heigh
    private static final double WIDTH = 700;
    private static final double HEIGHT = 330;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle("Iz.Lumin");

        // Set the application icon
        // stage.getIcons().add(new Image("path for image"));

        // Calculate the X and Y coordinates for bottom right corner
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double bottomRightX = screenWidth - (WIDTH + 9);
        double bottomRightY = screenHeight - (HEIGHT + 70);

        // Set the position of the stage
        stage.setX(bottomRightX);
        stage.setY(bottomRightY);

        stage.setResizable(false); // changed to cannot resize (BK)

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}