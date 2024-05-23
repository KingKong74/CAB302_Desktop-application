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

    @FXML
    private void initialize() {
        UserTimer userTimer = userTimerDAO.select(userEmail);
        if(userTimer != null){
            timerValue = userTimer.getTimerValue();
        }
        updateTimerLabel();
    }

    public static void toggleBurgerMenu(double x, double y) throws IOException {
        PopupController.handleOpenPopup("burger", x, y, null, 100, 40);
    }

    @FXML
    private void incrementCount() {
        if (timerValue < 60) {
            timerValue++;
            updateTimerLabel();
            savePreference();
        }
    }

    @FXML
    private void decrementCount() {
        if (timerValue > 15) {
            timerValue--;
            updateTimerLabel();
            savePreference();
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText(timerValue + " mins");
    }

    private void savePreference(){
        UserTimer preference = new UserTimer(userEmail, timerValue);
        UserTimer existingPreference = userTimerDAO.select(userEmail);
        if (existingPreference == null) {
            userTimerDAO.insert(preference);
        } else {
            userTimerDAO.update(preference);
        }
    }
}
