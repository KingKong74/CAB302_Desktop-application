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
    private int notificationTime = 60 * 60; // Predefined duration in seconds

    @FXML
    protected void pushedTimer(ActionEvent event) {
        if (timeline == null) {
            startTimer();
            timerButton.setText("▶");
        } else {
            stopTimer();
        }
    }

    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            notificationTime--;
            updateButtonLabel();
            if (notificationTime <= 0) {
                stopTimer();
                System.out.println("Timer ended!");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Set the cycle count to indefinite
        timeline.play();
        updateButtonLabel();
    }

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
            updateButtonLabel();
        }
    }

    private void updateButtonLabel() {
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




