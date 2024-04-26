package com.example.main_sem_proj.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public Button loginButton;
    public Button trollButton;

    private final int WIDTH = 580;
    private final int HEIGHT = 270;

    @FXML
    protected void onTrollButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/main_sem_proj/view/troll-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Troll'd'");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Go to Log in Window
        LoginController.start();

        stage.close();
    }


}

// (BK)....
// Set the application icon
// stage.getIcons().add(new Image("path for image"));


