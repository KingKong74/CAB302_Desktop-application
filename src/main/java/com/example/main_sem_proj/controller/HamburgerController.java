package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.database.SqliteUserTimerDAO;
import static com.example.main_sem_proj.controller.MainController.userEmail;

import com.example.main_sem_proj.model.users.UserTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HamburgerController extends PopupController {

    @FXML
    private Label timerLabel;
    private int timerValue = 45;
    private final SqliteUserTimerDAO userTimerDAO = new SqliteUserTimerDAO();

    /**
     * Initializes the HamburgerController by loading the user's timer preference from the database
     * and updating the timer label.
     */
    @FXML
    private void initialize() {
        UserTimer userTimer = userTimerDAO.select(userEmail);
        if (userTimer != null) {
            timerValue = userTimer.getTimerValue();
        }
        updateTimerLabel();
    }

    /**
     * Toggles the burger menu popup at the specified coordinates.
     *
     * @param x the x-coordinate where the popup should appear
     * @param y the y-coordinate where the popup should appear
     * @throws IOException if there is an issue opening the popup
     */
    public static void toggleBurgerMenu(double x, double y) throws IOException {
        PopupController.handleOpenPopup("burger", x, y, null, 100, 40);
    }

    /**
     * Increments the timer value by 1 minute, updates the label, and saves the preference.
     * Ensures the timer value does not exceed 60 minutes.
     */
    @FXML
    private void incrementCount() {
        if (timerValue < 60) {
            timerValue++;
            updateTimerLabel();
            savePreference();
        }
    }

    /**
     * Decrements the timer value by 1 minute, updates the label, and saves the preference.
     * Ensures the timer value does not go below 15 minutes.
     */
    @FXML
    private void decrementCount() {
        if (timerValue > 15) {
            timerValue--;
            updateTimerLabel();
            savePreference();
        }
    }

    /**
     * Updates the timer label with the current timer value in minutes.
     */
    private void updateTimerLabel() {
        timerLabel.setText(timerValue + " mins");
    }

    /**
     * Saves the user's timer preference to the database.
     * If the preference exists, it updates the existing record; otherwise, it inserts a new record.
     */
    private void savePreference() {
        UserTimer preference = new UserTimer(userEmail, timerValue);
        UserTimer existingPreference = userTimerDAO.select(userEmail);
        if (existingPreference == null) {
            userTimerDAO.insert(preference);
        } else {
            userTimerDAO.update(preference);
        }
    }
}
