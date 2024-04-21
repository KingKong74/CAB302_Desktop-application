package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {

    /**
     * Handles the click event of the login button to open the main GUI.
     */
    @FXML
    protected static void ButtonClick() {
        final String TITLE = "Iz.Lumin";
        final int WIDTH = 580;
        final int HEIGHT = 270;

        try {
            Stage stage = createStage(TITLE, WIDTH, HEIGHT);
            setStagePosition(stage, WIDTH, HEIGHT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new stage for the main GUI.
     *
     * @param title  The title of the stage.
     * @param width  The width of the stage.
     * @param height The height of the stage.
     * @return The created stage.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private static Stage createStage(String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setResizable(false);  // Disable user resizing
        stage.setScene(scene);
        return stage;
    }

    /**
     * Sets the position of the stage to the bottom right corner of the screen.
     *
     * @param stage The stage to set the position for.
     */
    private static void setStagePosition(Stage stage, double width, double height) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double bottomRightX = screenWidth - (width + 9);
        double bottomRightY = screenHeight - (height + 75);
        stage.setX(bottomRightX);
        stage.setY(bottomRightY);
    }

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
            notificationTime = 45; // Reset remaining seconds
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
}




