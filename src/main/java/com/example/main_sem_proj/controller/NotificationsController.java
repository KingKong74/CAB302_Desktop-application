package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import com.example.main_sem_proj.model.database.SqliteUserNotificationDAO;
import com.example.main_sem_proj.model.users.UserNotification;
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
import static com.example.main_sem_proj.controller.MainController.userEmail;

import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NotificationsController{

    @FXML
    private Label titleLabel;
    @FXML
    private Label messageLabel;
    private String messageText;
    private String titleText;
    private final int HEIGHT = 70;
    private final int WIDTH = 150;

    private final SqliteUserNotificationDAO userNotificationDAO = new SqliteUserNotificationDAO();

    public void initialize(){
        loadUserSettings();
    }

    private void loadUserSettings() {
        UserNotification userNotification = userNotificationDAO.select(userEmail);
        if (userNotification != null){
            initializeUserSettings(userNotification);
        }
    }

    private void initializeUserSettings(UserNotification userNotification) {
        if(userNotification.getCustomMessage()){
           setMessageLabel(userNotification.getNotificationText());
           setTitleLabel(userNotification.getNotificationTitle());
        }
        if (userNotification.getCustomSound()){
            TimerController.setNotificationSound(userNotification.getSoundEffect());
        }
        if (userNotification.getSoftAlert()){
            TimerController.setNotificiationVolume(0.1);
        }
    }

    public void displayNotification() {
        Point2D notificationPosition = calculateStagePosition();
        Stage notificationStage = createNotificationWindow(notificationPosition.getX(), notificationPosition.getY());
        if (notificationStage != null) {
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
        Timeline closeNotificationTimeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            stage.close();
        }));
        closeNotificationTimeline.play();
    }


    public Point2D calculateStagePosition() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double x = screenBounds.getMaxX() - WIDTH;
        double y = screenBounds.getMinY() + screenBounds.getHeight() / 4;

        return new Point2D(x,y);
    }

    public void setMessageLabel(String message){
        messageLabel.setText(message);
    }

    public void setTitleLabel(String title){
        titleLabel.setText(title);
    }
}




