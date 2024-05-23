package com.example.main_sem_proj.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HamburgerController extends PopupController {

    @FXML
    private Label timerLabel;
    private int timerValue = 45;

    @FXML
    private void initialize() {
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
        }
    }

    @FXML
    private void decrementCount() {
        if (timerValue > 15) {
            timerValue--;
            updateTimerLabel();
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText(timerValue + " mins");
    }
}
