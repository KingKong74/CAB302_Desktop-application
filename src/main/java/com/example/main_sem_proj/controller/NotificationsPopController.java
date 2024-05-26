package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.database.SqliteUserNotificationDAO;
import com.example.main_sem_proj.model.users.UserNotification;
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
    public Label titleLab;
    public Label textLab;
    public Label soundLabel;
    private final SqliteUserNotificationDAO userNotificationDAO = new SqliteUserNotificationDAO();
    private final NotificationsController notification = new NotificationsController();

    @FXML
    private ChoiceBox<String> dropdownMenu;

    public void initialize() {
        dropdownMenu.setValue("Notification sound");
        setChoiceBoxEnabled(false);
        setFieldsEnabled(false);

        loadUserSettings();
    }


    @FXML
    protected static void ButtonClick(double x, double y) {
        handleOpenPopup(VIEW, x, y, TITLE);
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

    private void loadUserSettings(){
        UserNotification userNotification = userNotificationDAO.select(userEmail);
        if (userNotification != null) {
            intializeUserSetting(userNotification);
        }
    }

    private void intializeUserSetting(UserNotification userNotification) {
        if (userNotification.getCustomSound() != null){
            boolean customSoundCheck = userNotification.getCustomSound();
            soundEffectsCheckBox.setSelected(customSoundCheck);
            if(customSoundCheck){
                dropdownMenu.setDisable(false);
                dropdownMenu.setValue(userNotification.getSoundEffect());
            }
        }

        if (userNotification.getCustomMessage() != null){
            boolean customMessageCheck = userNotification.getCustomMessage();
            customNotificationCheckBox.setSelected(customMessageCheck);
            if(customMessageCheck){
                notifTitleField.setDisable(false);
                notifTextField.setDisable(false);
                notifTitleField.setText(userNotification.getNotificationText());
                notifTextField.setText(userNotification.getNotificationTitle());
            }
        }

        if (userNotification.getSoftAlert() != null){
            boolean softAlertCheck = userNotification.getSoftAlert();
            softAlertsCheckBox.setSelected(softAlertCheck);
        }
    }

    public void handleNotificationSave() {
        UserNotification preference = new UserNotification(
                userEmail,
                soundEffectsCheckBox.isSelected(),
                customNotificationCheckBox.isSelected(),
                softAlertsCheckBox.isSelected(),
                notifTitleField.getText(),
                notifTextField.getText(),
                dropdownMenu.getValue()
        );

        UserNotification existingPreference = userNotificationDAO.select(userEmail);
        if (existingPreference == null){
            userNotificationDAO.insert(preference);
        }else {
            userNotificationDAO.update(preference);
        }
        if (soundEffectsCheckBox.isSelected()){
            TimerController.setNotificationSound(preference.getSoundEffect());
        }else {
            TimerController.setNotificationSound("Notification sound");
        }
        if (softAlertsCheckBox.isSelected()){
            TimerController.setNotificiationVolume(0.1);
        }else {
            TimerController.setNotificiationVolume(1.0);
        }

        closePopup();
    }
}

