package com.example.main_sem_proj.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

/**
 * The controller class for the Main view of the application.
 * This class handles the user interactions in the Main view.
 */
public class MainController {

    @FXML
    public VBox mainPage;
    public Button darkModeButton;
    public Button focusModeButton;
    public Button notificationsButton;
    public Button settingsButton;
    public Button signoutButton;
    public HBox timerBox;
    public ChoiceBox dropdownMenu;

    public Label scheduleLabel;

    @FXML
    private Label welcomeLabel;

    private final TimerController timerController = new TimerController(this::updateButtonLabel);


    public void setWelcomeLabel(String welcomeMessage) {
        welcomeLabel.setText(welcomeMessage);
    }

    public void setScheduleLabel(String scheduleMessage) {scheduleLabel.setText(scheduleMessage);}


    //
    // Switch
    //

    private int count;
    @FXML
    protected void onDarkModeButtonClick() {
        count++;
        if (count == 1)
        {
            darkMode();
        }
        if (count == 2 || count == 0)
        {
            lightMode();
        }
    }
    protected void lightMode(){
        darkModeButton.setText("Dark Mode");

        timerButton.setStyle("-fx-background-color: E0E0E0;");

        dropdownMenu.setStyle("-fx-background-color: E0E0E0;");
        dropdownMenu.lookup(".label").setStyle("-fx-text-fill: black;");

        mainPage.setStyle("-fx-background-color: white");
        welcomeLabel.setStyle("-fx-text-fill: black");

//        sliderValue.setStyle("-fx-text-fill: black");
//        colourSlider.lookup(".thumb").setStyle("-fx-background-color: white;");

        signoutButton.setStyle("-fx-background-color: BFBEBE; -fx-text-fill: black;");

        darkModeButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
        notificationsButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
        settingsButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
        count = count - 2;
    }

    protected void darkMode(){
        darkModeButton.setText("Light Mode");

        timerButton.setStyle("-fx-background-color: #414141; -fx-text-fill: white;");

        dropdownMenu.setStyle("-fx-background-color: #414141;");
        dropdownMenu.lookup(".label").setStyle("-fx-text-fill: white;");

        mainPage.setStyle("-fx-background-color:#252525");
        welcomeLabel.setStyle("-fx-text-fill: white");

//        sliderValue.setStyle("-fx-text-fill: white");
//        colourSlider.lookup(".thumb").setStyle("-fx-background-color: grey;");

        signoutButton.setStyle("-fx-background-color: #353435; -fx-text-fill: white;");

        darkModeButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141; -fx-text-fill: white; -fx-padding: 0 0 0 0;");
        notificationsButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141;-fx-text-fill: white; -fx-padding: 0 0 0 0;");
        settingsButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141;-fx-text-fill: white; -fx-padding: 0 0 0 0;");
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

    @FXML
    public void pushedTimer() {
        if (timerController.getTimeline() == null) {
            timerController.startTimer();
            timerButton.setText("⏵");
        } else {
            timerController.stopTimer();
        }
    }

    private void updateButtonLabel() {
        String timeString = timerController.formatTime(timerController.getNotificationTime());
        timerButton.setText(timeString);
    }

    //
    // Sign out
    //
    @FXML
    protected void onSignoutButtonClick(ActionEvent event) throws IOException {
//        stopTimer();
        System.out.println("User Signed out");
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginController.openLoginWindow(stageToClose);
    }
}