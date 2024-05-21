package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.controller.PopupController;
import javafx.fxml.FXML;

public class SettingsPopController extends PopupController {

    private static final String TITLE = "Settings";
    private static final String VIEW = "settingsPop";

    @FXML
    protected static void ButtonClick(double x, double y) {
        PopupController.handleOpenPopup(VIEW, x, y, TITLE);
    }



}
