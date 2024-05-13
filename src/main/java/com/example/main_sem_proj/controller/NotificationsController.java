package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NotificationsController{

    @FXML
    private Label messageLabel;
    private final int HEIGHT = 50;
    private final int WIDTH = 150;

    public void displayNotification() {
        Point2D notificationPosition = calculateStagePosition();
        Stage notificationStage = createNotificationWindow(notificationPosition.getX(), notificationPosition.getY());
        if (notificationStage != null) {
            // Schedule the notification window to close after 3 seconds
            scheduleNotificationClose(notificationStage);
        }
    }

    private Stage createNotificationWindow(double x, double y) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/notifications-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            Stage stage = new Stage();
            stage.setOpacity(0.9);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setX(x);
            stage.setY(y);
            stage.show();
            return stage;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private void scheduleNotificationClose(Stage stage) {
        Timeline closeNotificationTimeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            stage.close();
        }));
        closeNotificationTimeline.play();
    }

    public void updateNotification(String message) {messageLabel.setText(message);}

    public Point2D calculateStagePosition() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double x = screenBounds.getMaxX() - WIDTH;
        double y = screenBounds.getMinY() + screenBounds.getHeight() / 4;

        return new Point2D(x,y);
    }
}




