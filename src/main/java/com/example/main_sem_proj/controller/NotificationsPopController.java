package com.example.main_sem_proj.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NotificationsPopController extends PopupController {

    private static final String TITLE = "Notifications";
    private static final String VIEW = "notificationsPop";
    public CheckBox soundEffectsCheckBox;
    public CheckBox customNotificationCheckBox;
    public CheckBox softAlertsCheckBox;
    public TextField notifTextField;
    public TextField notifTitleField;
    public Label titleLabel;
    public Label textLabel;
    public Label soundLabel;

    @FXML
    private ChoiceBox<String> dropdownMenu;

    public void initialize() {
        dropdownMenu.setValue("Notification sound");
        setChoiceBoxEnabled(false);
        setFieldsEnabled(false);
    }


    @FXML
    protected static void ButtonClick(double x, double y) {
        handleOpenPopup(VIEW, x, y, TITLE);
    }

    public void handleNotificationSave(ActionEvent actionEvent) throws IOException {
        PopupController.closePopup();
    }

    public void handleSoundEffectsCheckBox() {
        boolean enabled = soundEffectsCheckBox.isSelected();
        setChoiceBoxEnabled(enabled);
    }

    public void handleCustomNotificationCheckBox() {
        boolean enabled = customNotificationCheckBox.isSelected();
        setFieldsEnabled(enabled);
    }

    public void handleSoftAlertsCheckBox() {
        boolean enabled = softAlertsCheckBox.isSelected();
    }

    private void setChoiceBoxEnabled(boolean enabled) {
        dropdownMenu.setDisable(!enabled);
    }

    private void setFieldsEnabled(boolean enabled) {
        notifTextField.setDisable(!enabled);
        notifTitleField.setDisable(!enabled);
    }
}

