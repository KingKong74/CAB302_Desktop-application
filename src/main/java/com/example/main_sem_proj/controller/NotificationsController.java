package com.example.main_sem_proj.controller;

import javafx.fxml.FXML;

public class NotificationsController extends MainController {

    private static final String TITLE = "Notifications";
    private static final String VIEW = "notifications";

    @FXML
    protected static void ButtonClick(double x, double y) {
        PopupController.ButtonClick(VIEW, x, y, TITLE);
    }
}

