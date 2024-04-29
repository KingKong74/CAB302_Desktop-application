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
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The controller class for the Main view of the application.
 * This class handles the user interactions in the Main view.
 */
public class MainController {

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
    /**
     * Sets the position of the stage to the bottom right corner of the screen.
     *
     * @param stage The stage to set the position for.
     */
    public static void setStagePosition(Stage stage, double width, double height) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double bottomRightX = screenWidth - (width + 9);
        double bottomRightY = screenHeight - (height + 75);
        stage.setX(bottomRightX);
        stage.setY(bottomRightY);
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

//    public void initialize() {
//        colourSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            // Get the value of the slider
//            double sliderValue = newValue.doubleValue();
//
//            // Calculate the color temperature based on the slider value
//            double temperature = calculateColorTemperature(sliderValue);
//
//            // Map the color temperature to a color gradient from dark orange to normal (white)
//            Color color = mapColorTemperatureToGradient(temperature);
//
//            // Set the background color of the main pane
//            mainPane.setBackground(new Background(new BackgroundFill(
//                    color,
//                    null,
//                    null
//            )));
//        });
//    }

//    private double calculateColorTemperature(double sliderValue) {
//        // Implement your calculation logic here
//        // Example: Linear mapping from slider value to color temperature range
//        double minSliderValue = 1900;
//        double maxSliderValue = 6500;
//        double minTemperature = 1900;
//        double maxTemperature = 6500;
//        return minTemperature + (maxTemperature - minTemperature) * ((sliderValue - minSliderValue) / (maxSliderValue - minSliderValue));
//    }
//
//    private Color mapColorTemperatureToGradient(double temperature) {
//        // Implement your color mapping logic here
//        // Example: Linear interpolation from dark orange to white
//        Color darkOrange = Color.rgb(255, 140, 0);
//        Color normalWhite = Color.WHITE;
//        double ratio = (temperature - 1900) / (6500 - 1900);
//        return darkOrange.interpolate(normalWhite, ratio);
//    }


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

    @FXML
    protected void onDarkModeButtonClick() {
        handleSwitchClick(Setting.DARK_MODE);
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
    protected void onSignoutButtonClick(ActionEvent event) {
        System.out.println("User Signed out");
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginController.start(stageToClose);
    }
}




