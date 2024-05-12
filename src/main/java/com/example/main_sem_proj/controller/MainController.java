package com.example.main_sem_proj.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The controller class for the Main view of the application.
 * This class handles the user interactions in the Main view.
 */
public class MainController {

    public Label scheduleLabel;

    @FXML
    private Label welcomeLabel;
    NotificationsController notification = new NotificationsController();

    public void setWelcomeLabel(String welcomeMessage) {
        welcomeLabel.setText(welcomeMessage);
    }

    public void setScheduleLabel(String scheduleMessage) {scheduleLabel.setText(scheduleMessage);}


    //
    // Switch
    //

    @FXML
    protected void onDarkModeButtonClick() {
        System.out.println("Dark mode enabled");
        setScheduleLabel("Sunrise in 8hrs, Bedtime in 1hr ...");
        notification.displayNotification();
    }

    //
    // Pop up windows
    //

    public static boolean isPopupOpen = false;

    @FXML
    protected void onNotificationsButtonClick(MouseEvent event) {
        double[] coordinates = getScreenCoordinates(event);
        double x = coordinates[0];
        double y = coordinates[1];

        NotificationsPopController.ButtonClick(x, y);
    }

    @FXML
    protected void onSettingsButtonClick(MouseEvent event) {
        double[] coordinates = getScreenCoordinates(event);
        double x = coordinates[0];
        double y = coordinates[1];
        SettingsPopController.ButtonClick(x, y);
    }

    /**
     * Get the screen coordinates of the mouse event.
     *
     * @param event The MouseEvent containing the screen coordinates.
     * @return An array containing the x and y coordinates.
     */
    private double[] getScreenCoordinates(MouseEvent event) {
        double x = event.getScreenX();
        double y = event.getScreenY();
        return new double[]{x, y};
    }


    //
    // Timer
    //

    @FXML
    private Button timerButton;

    private Timeline timeline;
    private int notificationTime = 6; // Predefined duration in seconds

    @FXML
    protected void pushedTimer(ActionEvent event) {
        if (timeline == null) {
            startTimer();
            timerButton.setText("⏵");
        } else {
            stopTimer();
        }
    }

    public void startTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null; // Reset the timeline reference
            updateButtonLabel(); // Update the button label
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            notificationTime--;
            System.out.println(notificationTime);
            if (notificationTime == 0) {
                System.out.println("It is in the if statement!");
//                stopTimer(); // Stop the timer when notificationTime reaches 0
                notification.displayNotification();
                System.out.println("Notification displayed!");
                timeline.stop(); // Stop the timeline after displaying the notification
                hideNotificationAfterDelay(); // Call a method to hide the notification after a delay
                System.out.println("Timer ended!");
            }
            updateButtonLabel();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void hideNotificationAfterDelay() {
        int hide_notificationTime = 3;
        Timeline hideTimeline = new Timeline(new KeyFrame(Duration.seconds(hide_notificationTime), e -> {
            try {
                notification.hideNotification();
                System.out.println("Notification hidden!");
            } catch (Exception ex) {
                System.out.println("Window already closed.");
            }
        }));
        hideTimeline.play();
    }


    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
            updateButtonLabel();
        }
    }

    private void updateButtonLabel() {
        if (notificationTime <= 0) {
            // Reset the timer
            notificationTime = 6;
//            stopTimer();
        }

        int hours = notificationTime / 3600;
        int minutes = (notificationTime % 3600) / 60;
        int seconds = notificationTime % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerButton.setText(timeString);
    }


    //
    // Sign out
    //
    @FXML
    protected void onSignoutButtonClick(ActionEvent event) throws IOException {
        System.out.println("User Signed out");
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginController.openLoginWindow(stageToClose);
    }
}




