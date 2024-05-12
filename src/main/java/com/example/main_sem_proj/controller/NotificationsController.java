package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

public class NotificationsController{

    @FXML
    private Label messageLabel;

    public void displayNotification() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/notifications-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 200,100);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void hideNotification(Stage stageToClose) {
        stageToClose.close();
    }

    public void updateNotification(String message) {messageLabel.setText(message);}
}


