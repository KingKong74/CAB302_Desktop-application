package com.example.main_sem_proj.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * The controller class for the Main view of the application.
 * This class handles the user interactions in the Main view.
 */
public class MainController {

    public VBox mainPage;
    @FXML
    private Slider colourSlider;
    @FXML
    public Label sliderValue;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Pane mainPane;

    public void setWelcomeLabel(String welcomeMessage) {
        welcomeLabel.setText(welcomeMessage);
    }


    //
    // Colour Slider
    //

    public void initialize() {
        if (colourSlider != null) {
            colourSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                // Update the label text with the integer value
                sliderValue.setText(String.format("%.0fK", newValue.doubleValue()));  // Format the value as a whole number
            });
        } else {
            System.err.println("An error occurred");
        }
    }

    //
    // Switches
    //

    public Setting getCurrentSetting() {
        return currentSetting;
    }

    public enum Setting {
        DARK_MODE,
        FOCUS_MODE,

        // TODO: add more settings later...
    }

    private Setting currentSetting = null;
    private int count;
    @FXML
    protected void onDarkModeButtonClick() {
        count++;
        handleSwitchClick(Setting.DARK_MODE);
        if (count == 1)
        {
            mainPage.setStyle("-fx-background-color:#252525");
            welcomeLabel.setStyle("-fx-text-fill: white");

        }
        if (count == 2)
        {
            mainPage.setStyle("-fx-background-color:#white");
            welcomeLabel.setStyle("-fx-text-fill: black");
            count = count - 2;
        }
    }

    @FXML
    protected void onFocusModeButtonClick() {
        handleSwitchClick(Setting.FOCUS_MODE);
    }

    //
    //TODO add others...
    //

    @FXML
    public void handleSwitchClick(Setting setting) {
        if (currentSetting != setting) {
            currentSetting = setting;
            handleSettingChange(setting);
        } else {
            System.out.println(currentSetting + " disabled");
            currentSetting = null;
        }
    }

    private void handleSettingChange(Setting setting) {
        switch (setting) {
            case DARK_MODE:
                System.out.println("Dark Mode enabled");
                break;
            case FOCUS_MODE:
                System.out.println("Focus Mode enabled");
                break;
            // TODO more settings for the other presets
        }
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

        NotificationsController.ButtonClick(x, y);
    }

    @FXML
    protected void onSettingsButtonClick(MouseEvent event) {
        double[] coordinates = getScreenCoordinates(event);
        double x = coordinates[0];
        double y = coordinates[1];
        SettingsController.ButtonClick(x, y);
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
            timerButton.setText("⏵");
        } else {
            stopTimer();
            //timerButton.setText("⏸");
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




