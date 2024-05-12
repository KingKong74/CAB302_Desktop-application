package com.example.main_sem_proj.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class NotificationsPopController extends PopupController {

    private static final String TITLE = "Notifications";
    private static final String VIEW = "notificationsPop";

    public void handleNotificationSave(ActionEvent actionEvent) throws IOException {
        PopupController.closePopup();
    }

    @FXML
    protected static void ButtonClick(double x, double y) {
        handleOpenPopup(VIEW, x, y, TITLE);
    }
}

