package com.example.main_sem_proj.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.main_sem_proj.controller.LoginController.openRegistrationWindow;

public class HelloController {

    public Button loginButton;

    /**
     * Handles the event when the hello button is clicked.
     * Loads the login-view.fxml file and sets the scene to the login window.
     */
    @FXML
    protected void onHelloButtonClick() {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/main_sem_proj/view/login-view.fxml"));
            Parent root = fxmlLoader.load();

            // Set the scene with the login window
            Scene scene = new Scene(root, 400, 250);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void handleSigninHyperlink(ActionEvent actionEvent) throws IOException {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openRegistrationWindow(stageToClose);
    }

}



