package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

public class NotificationsController {

    @FXML
    private Label messageLabel;
    @FXML
    private VBox rootNode;

    private Node getTopLevelNode() {
        return rootNode;
    }

    public void displayNotification() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/notifications-view.fxml"));
            Parent root = loader.load();
            NotificationsController controller = loader.getController();

            // Update the notification message label
            controller.updateNotification();

            Scene scene = new Scene(root, 200, 100);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void hideNotification() {
        Stage stageToClose = (Stage) getTopLevelNode().getScene().getWindow();
        stageToClose.close();
    }

    public void updateNotification() {
        // Retrieve the message from the messageLabel defined in FXML
        String message = messageLabel.getText();
        System.out.println("Retrieved message" + message);
        messageLabel.setText(message);
    }
}


