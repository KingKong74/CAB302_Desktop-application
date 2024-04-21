package com.example.main_sem_proj.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {

    public Button loginButton;

    private final int WIDTH = 580;
    private final int HEIGHT = 270;

    @FXML
    protected void onHelloButtonClick() {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Go to Main window
        MainController.ButtonClick();

        stage.close();
    }
}

// (BK)....
// Set the application icon
// stage.getIcons().add(new Image("path for image"));


