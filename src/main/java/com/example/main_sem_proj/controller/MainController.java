package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.database.SqliteUserSettingDAO;
import com.example.main_sem_proj.model.users.UserSetting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * The controller class for the Main view of the application.
 * This class handles the user interactions in the Main view.
 */
public class MainController {
    @FXML
    public VBox mainPage;
    @FXML
    public Button darkModeButton;
    @FXML
    public Button notificationsButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button signoutButton;
    @FXML
    public HBox timerBox;
    @FXML
    public ChoiceBox dropdownMenu;
    @FXML
    public Label scheduleLabel;
    @FXML
    public ToggleButton hamburgerButton;
    @FXML
    private Label welcomeLabel;
    private Timeline timeline;
    private Timeline initialize;

    private LoginController loginController;
    private final TimerController timerController = new TimerController(this::updateButtonLabel);
    private final SqliteUserSettingDAO userSettingDAO = new SqliteUserSettingDAO();
    NotificationsController notification = new NotificationsController();

    public void setWelcomeLabel(String welcomeMessage) {
        welcomeLabel.setText(welcomeMessage);
    }

    public void setScheduleLabel(String scheduleMessage) {scheduleLabel.setText(scheduleMessage);}

    public void setUserEmail(String email) { userEmail = email;}
    public static String userEmail;

    @FXML
    private void initialize(){
        startTimeline();
    }
    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), this::updateScheduleLabelText));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Method to set LoginController reference
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void updateScheduleLabelText(ActionEvent event) {
        // Retrieve the user's sleep schedule from the database
        UserSetting userSetting = userSettingDAO.select(userEmail);
        if (userSetting != null && Boolean.TRUE.equals(userSetting.getSleepSchedule())) {
            // Get the current time
            LocalTime currentTime = LocalTime.now();

            // Calculate the time until bedtime or wake-up time
            LocalTime bedtime = LocalTime.parse(userSetting.getBedTime());
            LocalTime wakeupTime = LocalTime.parse(userSetting.getWakeTime());

            if (currentTime.isBefore(bedtime)) {
                // Calculate time until bedtime
                Duration untilBedtime = Duration.between(currentTime, bedtime);
                setScheduleLabel(String.format("Bedtime in %d hour(s) %d minute(s)", untilBedtime.toHours(), untilBedtime.toMinutesPart()));
            } else {
                // Calculate time until wakeup time, accounting for wakeup time on the next day
                LocalTime adjustedWakeupTime = wakeupTime;
                if (currentTime.isAfter(wakeupTime)) {
                    adjustedWakeupTime = wakeupTime.plusHours(24);
                }
                Duration untilWakeup = Duration.between(currentTime, adjustedWakeupTime);
                setScheduleLabel(String.format("Wake-up in %d hour(s) %d minute(s)", untilWakeup.toHours(), untilWakeup.toMinutesPart()));
            }
        } else {
            // If the user's sleep schedule is not enabled, clear the schedule label text
            setScheduleLabel("");
        }
    }


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
            count = count - 2;
        }
    }
    //
    // Colour Modes
    //
    protected void lightMode(){
        darkModeButton.setText("Dark Mode");

        timerButton.setStyle("-fx-background-color: E0E0E0;");

        hamburgerButton.setStyle("-fx-background-color: E0E0E0;");
        hamburgerButton.setTextFill(Paint.valueOf("black"));

        dropdownMenu.setStyle("-fx-background-color: E0E0E0;");
        dropdownMenu.lookup(".label").setStyle("-fx-text-fill: black;");

        mainPage.setStyle("-fx-background-color: white");
        welcomeLabel.setStyle("-fx-text-fill: black");

        signoutButton.setStyle("-fx-background-color: BFBEBE; -fx-text-fill: black;");

        darkModeButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
        notificationsButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
        settingsButton.setStyle("-fx-background-radius: 10; -fx-background-color: E0E0E0; -fx-text-fill: black; -fx-padding: 0 0 0 0;");
    }

    protected void darkMode(){
        darkModeButton.setText("Light Mode");

        timerButton.setStyle("-fx-background-color: #414141; -fx-text-fill: white;");

        hamburgerButton.setStyle("-fx-background-color: #414141;");
        hamburgerButton.setTextFill(Paint.valueOf("white"));

        dropdownMenu.setStyle("-fx-background-color: #414141;");
        dropdownMenu.lookup(".label").setStyle("-fx-text-fill: white;");

        mainPage.setStyle("-fx-background-color:#252525");
        welcomeLabel.setStyle("-fx-text-fill: white");

        signoutButton.setStyle("-fx-background-color: #353435; -fx-text-fill: white;");

        darkModeButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141; -fx-text-fill: white; -fx-padding: 0 0 0 0;");
        notificationsButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141;-fx-text-fill: white; -fx-padding: 0 0 0 0;");
        settingsButton.setStyle("-fx-background-radius: 10; -fx-background-color: #414141;-fx-text-fill: white; -fx-padding: 0 0 0 0;");
    }

    //
    // Hamburger Button
    //

    public void toggleBurgerMenu(MouseEvent event) throws IOException {
        double[] coordinates = getScreenCoordinates(event);
        double x = coordinates[0];
        double y = coordinates[1];
        HamburgerController.toggleBurgerMenu(x, y);
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
        timerController.stopTimer();
        System.out.println("User Signed out");
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        LoginController.openLoginWindow(stageToClose);
    }



}