package com.example.main_sem_proj.controller;

import javafx.fxml.FXML;

public class NotificationsPopController extends PopupController {

    private static final String TITLE = "Notifications";
    private static final String VIEW = "notificationsPop";

    @FXML
    protected static void ButtonClick(double x, double y) {
        handleOpenPopup(VIEW, x, y, TITLE);
    }
}

