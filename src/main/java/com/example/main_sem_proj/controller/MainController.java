package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Window;

import java.io.IOException;

import static com.example.main_sem_proj.controller.ControllerUtils.popupStage;


public class MainController {

    /**
     * Handles the click event of login button to open main GUI
     */
    @FXML
    protected static void ButtonClick() {

        final String TITLE = "Iz.Lumin";
        final int WIDTH = 580;
        final int HEIGHT = 270;

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.setTitle(TITLE);
            stage.setResizable(false);  // no user resizing
            stage.setScene(scene);

            // Calculate the X and Y coordinates for bottom right corner
            double screenWidth = Screen.getPrimary().getBounds().getWidth();
            double screenHeight = Screen.getPrimary().getBounds().getHeight();
            double bottomRightX = screenWidth - (WIDTH + 9);
            double bottomRightY = screenHeight - (HEIGHT + 75);

            // Set the position of the stage
            stage.setX(bottomRightX);
            stage.setY(bottomRightY);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    static Popup currentPopup = null;
    public static boolean isPopupOpen = false;

    @FXML
    protected void onNotificationsButtonClick(MouseEvent event) {
        System.out.println("Notifications " + isPopupOpen);
        double x = event.getScreenX();
        double y = event.getScreenY();
        //System.out.println("Mouse clicked at: X=" + x + ", Y=" + y);

        handlePopupButtonClick(Popup.NOTIFICATIONS, () -> NotificationsController.ButtonClick(x, y));

    }

    @FXML
    protected void onSettingsButtonClick(MouseEvent event) {
        System.out.println("Settings " + isPopupOpen);
        double x = event.getScreenX();
        double y = event.getScreenY();
       // System.out.println("Mouse clicked at: X=" + x + ", Y=" + y);

        handlePopupButtonClick(Popup.SETTINGS, () -> SettingsController.ButtonClick(x, y));

    }

    /**
     * Handles the button click event for opening or closing a popup window.
     * If the specified popup type is already open, it closes the popup.
     * If no popup is currently open, it opens the specified popup and executes the button click action.
     * If another popup is already open, it closes the current popup and opens the specified one, then executes the button click action.
     *
     * @param popup          The type of popup to handle.
     * @param buttonClickAction The action to execute when the button is clicked.
     */
    private void handlePopupButtonClick(Popup popup, Runnable buttonClickAction) {
        if (currentPopup == popup) {
            popupStage.close();
            System.out.println("Closing popup");
            currentPopup = null;
            isPopupOpen = false;
        } else if (!isPopupOpen) {
            openPopup(popup);
            buttonClickAction.run();
        } else if (isPopupOpen){
            popupStage.close();
            openPopup(popup);
            buttonClickAction.run();
            System.out.println("hit True");
        } else {
            System.out.println("Error");
        }
    }

    protected void openPopup(Popup popup) {
        System.out.println("Opening " + popup.toString() + " popup");
    }

}



