package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;

public class NotificationsController extends MainController {
    @FXML
    private Label messageLabel;


    public void displayNotification(String message) {
//        updateNotification(message);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Notifications-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.NONE);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void hideNotification(Stage stageToClose) {
        stageToClose.close();
    }

    public void updateNotification(String message) {
        messageLabel.setText(message);
    }
}


