package com.example.main_sem_proj.controller;

import javafx.fxml.FXML;


public class MainController {
    public Setting getCurrentSetting() {
        return currentSetting;
    }

    public enum Setting {
        DARK_MODE,
        FOCUS_MODE,

        // TODO: add more settings later...
    }

    private Setting currentSetting = null;

    @FXML
    protected void onDarkModeButtonClick() {
        handleSwitchClick(Setting.DARK_MODE);
    }

    @FXML
    protected void onFocusModeButtonClick() {
        handleSwitchClick(Setting.FOCUS_MODE);
    }

    //
    //TODO add others...
    //

    @FXML
    public void handleSwitchClick(Setting setting) {
        if (currentSetting != setting) {
            currentSetting = setting;
            handleSettingChange(setting);
        }else {
            System.out.println( currentSetting + " disabled");
            currentSetting = null;
        }
    }

    private void handleSettingChange(Setting setting) {
        switch (setting) {
            case DARK_MODE:
                System.out.println("Dark Mode enabled");
                break;
            case FOCUS_MODE:
                System.out.println("Focus Mode enabled");
                break;
            // TODO more settings for the other presets
        }
    }

    enum Popup {
        NOTIFICATIONS,
        SETTINGS
    }

    private Popup currentPopup = null;

    @FXML
    protected void onNotificationsButtonClick() {
        handlePopup(Popup.NOTIFICATIONS);
    }
    @FXML
    protected void onSettingsButtonClick() {
        handlePopup(Popup.SETTINGS);
    }

    private void handlePopup(Popup popup) {
        if (currentPopup != popup) {
            closePopup();
            openPopup(popup);
        }
    }

    private void openPopup(Popup popup) {
        System.out.println("Opening " + popup.toString() + " popup");
        currentPopup = popup;
    }

    private void closePopup() {
        if (currentPopup != null) {
            System.out.println("Closing " + currentPopup.toString() + " popup");
            currentPopup = null;
        }
    }
}



