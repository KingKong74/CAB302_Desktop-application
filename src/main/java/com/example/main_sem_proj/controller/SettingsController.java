package com.example.main_sem_proj.controller;

import javafx.fxml.FXML;

public class SettingsController extends MainController {

    private static final String TITLE = "Settings";
    private static final String VIEW = "settings";

    @FXML
    protected static void ButtonClick(double x, double y) {
        PopupController.ButtonClick(VIEW, x, y, TITLE);
    }
}
