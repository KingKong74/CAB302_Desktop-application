package com.example.main_sem_proj.controller;
import com.example.main_sem_proj.model.SqliteUserDAO;
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/main_sem_proj/view/login-view.fxml"));
            Parent root = fxmlLoader.load();

            // Set the scene with the login window
            Scene scene = new Scene(root, 350, 200);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }



}



