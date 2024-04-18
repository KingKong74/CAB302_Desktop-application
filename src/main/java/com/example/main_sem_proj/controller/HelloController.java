package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public Button loginButton;
    private final int WIDTH = 700;
    private final int HEIGHT = 350;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setScene(scene);

        // Calculate the X and Y coordinates for bottom right corner
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double bottomRightX = screenWidth - (WIDTH + 9);
        double bottomRightY = screenHeight - (HEIGHT + 70);

        // Set the position of the stage
        stage.setX(bottomRightX);
        stage.setY(bottomRightY);

        stage.show();


    }
}

// (BK)....
// Set the application icon
// stage.getIcons().add(new Image("path for image"));


