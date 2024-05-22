package com.example.main_sem_proj.controller;
import com.example.main_sem_proj.model.TimeStringConverter;
import com.example.main_sem_proj.model.TimeTextFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SettingsPopController extends PopupController {

    @FXML
    private ComboBox<String> startTimeComboBox;
    @FXML
    private ComboBox<String> endTimeComboBox;
    @FXML
    private ComboBox<String> bedTimeComboBox;
    @FXML
    private ComboBox<String> wakeTimeComboBox;
    @FXML
    private CheckBox alertsCheckBox;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private CheckBox sleepCheckBox;
    @FXML
    private Label bedTimeLabel;
    @FXML
    private Label wakeUpLabel;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private CheckBox locationCheckBox;

    private static final String TITLE = "Settings";
    private static final String VIEW = "settingsPop";

    public void initialize() {
        initializeComboBox(startTimeComboBox);
        initializeComboBox(endTimeComboBox);
        initializeComboBox(bedTimeComboBox);
        initializeComboBox(wakeTimeComboBox);

        setComboBoxesEnabled(startTimeComboBox, false);
        setComboBoxesEnabled(endTimeComboBox, false);
        setComboBoxesEnabled(bedTimeComboBox, false);
        setComboBoxesEnabled(wakeTimeComboBox, false);

        setFieldsEnabled(false);
    }

    @FXML
    protected static void ButtonClick(double x, double y) {
        PopupController.handleOpenPopup(VIEW, x, y, TITLE);
    }

    private void initializeComboBox(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("12:15", "12:30", "12:45");
        for (int hour = 1; hour <= 11; hour++) {
            for (int minute = 0; minute < 60; minute += 15) {
                String time = String.format("%02d:%02d", hour, minute);
                comboBox.getItems().add(time);
            }
        }
        comboBox.getItems().add("12:00");

        comboBox.setConverter(new TimeStringConverter());
        comboBox.getEditor().setTextFormatter(new TimeTextFormatter(comboBox));
    }

    @FXML
    private void handleEnableAlerts() {
        boolean enabled = alertsCheckBox.isSelected();
        setComboBoxesEnabled(startTimeComboBox, enabled);
        setComboBoxesEnabled(endTimeComboBox, enabled);
        adjustLabelsOpacity(startTimeLabel, enabled);
        adjustLabelsOpacity(endTimeLabel, enabled);
    }

    @FXML
    private void handleEnableSleep(){
        boolean enabled = sleepCheckBox.isSelected();
        setComboBoxesEnabled(bedTimeComboBox, enabled);
        setComboBoxesEnabled(wakeTimeComboBox, enabled);
        adjustLabelsOpacity(bedTimeLabel, enabled);
        adjustLabelsOpacity(wakeUpLabel, enabled);
    }


    private void setComboBoxesEnabled(ComboBox<String> comboBox, boolean enabled) {
        comboBox.setDisable(!enabled);
    }

    private void adjustLabelsOpacity(Label label, boolean enabled) {
        final double DISABLED_OPACITY = 0.3;
        final double ENABLED_OPACITY = 1.0;

        label.setOpacity(enabled ? ENABLED_OPACITY : DISABLED_OPACITY);
    }

    @FXML
    private void handleEnableLocation() {
        boolean enabled = locationCheckBox.isSelected();
        setFieldsEnabled(enabled);
    }

    private void setFieldsEnabled(boolean enabled) {
        countryTextField.setDisable(!enabled);
        cityTextField.setDisable(!enabled);
    }


}
