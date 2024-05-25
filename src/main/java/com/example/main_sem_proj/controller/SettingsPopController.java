package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.TimeStringConverter;
import com.example.main_sem_proj.model.TimeTextFormatter;
import com.example.main_sem_proj.model.database.SqliteUserSettingDAO;
import com.example.main_sem_proj.model.users.UserSetting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.example.main_sem_proj.controller.MainController.userEmail;

public class SettingsPopController extends PopupController {
    @FXML private ComboBox<String> startTimeComboBox;
    @FXML private ComboBox<String> endTimeComboBox;
    @FXML private ComboBox<String> bedTimeComboBox;
    @FXML private ComboBox<String> wakeTimeComboBox;
    @FXML private CheckBox alertsCheckBox;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private CheckBox sleepCheckBox;
    @FXML private Label bedTimeLabel;
    @FXML private Label wakeUpLabel;
    @FXML private TextField countryTextField;
    @FXML private TextField cityTextField;
    @FXML private CheckBox locationCheckBox;
    @FXML private ToggleButton amToggleStart;
    @FXML private ToggleButton pmToggleStart;
    @FXML private ToggleGroup startTime;
    @FXML private ToggleButton amToggleEnd;
    @FXML private ToggleButton pmToggleEnd;
    @FXML private ToggleGroup endTime;
    @FXML private ToggleButton amToggleBed;
    @FXML private ToggleButton pmToggleBed;
    @FXML private ToggleGroup bedTime;
    @FXML private ToggleButton amToggleWake;
    @FXML private ToggleButton pmToggleWake;
    @FXML private ToggleGroup wakeTime;

    private static final String TITLE = "Settings";
    private static final String VIEW = "settingsPop";
    private final SqliteUserSettingDAO userSettingDAO = new SqliteUserSettingDAO();

    /**
     * Initializes the settings popup controller.
     */
    public void initialize() {
        initializeComboBox(startTimeComboBox);
        initializeComboBox(endTimeComboBox);
        initializeComboBox(bedTimeComboBox);
        initializeComboBox(wakeTimeComboBox);

        disableInitialSettings();
        initializeToggleGroups();
        loadUserSettings();
    }

    /**
     * Initializes the toggle groups for AM/PM buttons.
     */
    private void initializeToggleGroups() {
        startTime = new ToggleGroup();
        initializeToggleGroup(startTime, amToggleStart, pmToggleStart, startTimeComboBox);

        endTime = new ToggleGroup();
        initializeToggleGroup(endTime, amToggleEnd, pmToggleEnd, endTimeComboBox);

        bedTime = new ToggleGroup();
        initializeToggleGroup(bedTime, amToggleBed, pmToggleBed, bedTimeComboBox);

        wakeTime = new ToggleGroup();
        initializeToggleGroup(wakeTime, amToggleWake, pmToggleWake, wakeTimeComboBox);
    }

    /**
     * Initializes a toggle group with AM/PM toggle buttons and links it to a ComboBox.
     *
     * @param group      the ToggleGroup to initialize
     * @param amToggle   the AM ToggleButton
     * @param pmToggle   the PM ToggleButton
     * @param comboBox   the ComboBox linked to the toggle group
     */
    private void initializeToggleGroup(ToggleGroup group, ToggleButton amToggle, ToggleButton pmToggle, ComboBox<String> comboBox) {
        amToggle.setToggleGroup(group);
        pmToggle.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            } else {
                updateComboBoxTime(amToggle, pmToggle, comboBox);
            }
        });
    }

    /**
     * Disables the initial settings in the UI.
     */
    private void disableInitialSettings() {
        setComboBoxesEnabled(startTimeComboBox, false);
        setComboBoxesEnabled(endTimeComboBox, false);
        setComboBoxesEnabled(bedTimeComboBox, false);
        setComboBoxesEnabled(wakeTimeComboBox, false);

        setFieldsEnabled(false);

        amToggleStart.setDisable(true);
        pmToggleStart.setDisable(true);
        amToggleEnd.setDisable(true);
        pmToggleEnd.setDisable(true);
        amToggleBed.setDisable(true);
        pmToggleBed.setDisable(true);
        amToggleWake.setDisable(true);
        pmToggleWake.setDisable(true);
    }

    /**
     * Loads the user settings from the database.
     */
    private void loadUserSettings() {
        UserSetting userSetting = userSettingDAO.select(userEmail);
        if (userSetting != null) {
            initializeUserSetting(userSetting);
        }
    }

    /**
     * Initializes the UI components with the user's settings.
     *
     * @param userSetting the UserSetting object containing the user's settings
     */
    private void initializeUserSetting(UserSetting userSetting) {
        if (userSetting.getAlertSchedule() != null) {
            boolean alertSchedule = userSetting.getAlertSchedule();
            alertsCheckBox.setSelected(alertSchedule);
            updateAlertSettings(alertSchedule);
            if (alertSchedule) {
                startTimeComboBox.setValue(userSetting.getStartTime());
                endTimeComboBox.setValue(userSetting.getEndTime());
            }
        }

        if (userSetting.getSleepSchedule() != null) {
            boolean sleepSchedule = userSetting.getSleepSchedule();
            sleepCheckBox.setSelected(sleepSchedule);
            updateSleepSettings(sleepSchedule);
            if (sleepSchedule) {
                bedTimeComboBox.setValue(userSetting.getBedTime());
                wakeTimeComboBox.setValue(userSetting.getWakeTime());
            }
        }

        if (userSetting.getLocation() != null) {
            boolean location = userSetting.getLocation();
            locationCheckBox.setSelected(location);
            setFieldsEnabled(location);
            if (location) {
                countryTextField.setText(userSetting.getCountry());
                cityTextField.setText(userSetting.getCity());
            }
        }
    }

    /**
     * Checks if the PM toggle button is selected.
     *
     * @param toggleGroup the ToggleGroup to check
     * @param pmToggle    the PM ToggleButton
     * @return true if PM is selected, false otherwise
     */
    private boolean isPmSelected(ToggleGroup toggleGroup, ToggleButton pmToggle) {
        return toggleGroup.getSelectedToggle() == pmToggle;
    }

    /**
     * Checks if the AM toggle button is selected.
     *
     * @param toggleGroup the ToggleGroup to check
     * @param amToggle    the AM ToggleButton
     * @return true if AM is selected, false otherwise
     */
    private boolean isAmSelected(ToggleGroup toggleGroup, ToggleButton amToggle) {
        return toggleGroup.getSelectedToggle() == amToggle;
    }

    /**
     * Updates the ComboBox time based on the selected AM/PM toggle.
     *
     * @param amToggle   the AM ToggleButton
     * @param pmToggle   the PM ToggleButton
     * @param comboBox   the ComboBox containing the time
     */
    private void updateComboBoxTime(ToggleButton amToggle, ToggleButton pmToggle, ComboBox<String> comboBox) {
        if (comboBox == null) return; // Null check for ComboBox

        String selectedTime = comboBox.getValue();
        if (selectedTime != null && isValidTime(selectedTime)) {
            int hours = Integer.parseInt(selectedTime.split(":")[0]);
            if (isPmSelected((ToggleGroup) amToggle.getToggleGroup(), pmToggle) && hours < 12) {
                hours += 12;
            } else if (isAmSelected((ToggleGroup) pmToggle.getToggleGroup(), amToggle) && hours >= 12) {
                hours -= 12;
            }
            String updatedTime = String.format("%02d:%s", hours, selectedTime.split(":")[1]);
            comboBox.setValue(updatedTime);
        }
    }

    /**
     * Validates if the given time is in the HH:MM format.
     *
     * @param time the time string to validate
     * @return true if the time is valid, false otherwise
     */
    private boolean isValidTime(String time) {
        return time.matches("^([01]?\\d|2[0-3]):[0-5]\\d$");
    }

    /**
     * Initializes a ComboBox with time values.
     *
     * @param comboBox the ComboBox to initialize
     */
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

    /**
     * Handles enabling or disabling alerts settings.
     */
    @FXML
    private void handleEnableAlerts() {
        boolean enabled = alertsCheckBox.isSelected();
        updateAlertSettings(enabled);
    }

    /**
     * Updates the alerts settings in the UI.
     *
     * @param enabled true to enable, false to disable
     */
    private void updateAlertSettings(boolean enabled) {
        setComboBoxesEnabled(startTimeComboBox, enabled);
        setComboBoxesEnabled(endTimeComboBox, enabled);
        adjustLabelsOpacity(startTimeLabel, enabled);
        adjustLabelsOpacity(endTimeLabel, enabled);
        amToggleStart.setDisable(!enabled);
        pmToggleStart.setDisable(!enabled);
        amToggleEnd.setDisable(!enabled);
        pmToggleEnd.setDisable(!enabled);
    }

    /**
     * Handles enabling or disabling sleep settings.
     */
    @FXML
    private void handleEnableSleep() {
        boolean enabled = sleepCheckBox.isSelected();
        updateSleepSettings(enabled);
    }

    /**
     * Updates the sleep settings in the UI.
     *
     * @param enabled true to enable, false to disable
     */
    private void updateSleepSettings(boolean enabled) {
        setComboBoxesEnabled(bedTimeComboBox, enabled);
        setComboBoxesEnabled(wakeTimeComboBox, enabled);
        adjustLabelsOpacity(bedTimeLabel, enabled);
        adjustLabelsOpacity(wakeUpLabel, enabled);
        amToggleBed.setDisable(!enabled);
        pmToggleBed.setDisable(!enabled);
        amToggleWake.setDisable(!enabled);
        pmToggleWake.setDisable(!enabled);
    }

    /**
     * Handles enabling or disabling location settings.
     */
    @FXML
    private void handleEnableLocation() {
        boolean enabled = locationCheckBox.isSelected();
        setFieldsEnabled(enabled);
    }

    /**
     * Sets the enabled state of a ComboBox.
     *
     * @param comboBox the ComboBox to set
     * @param enabled  true to enable, false to disable
     */
    private void setComboBoxesEnabled(ComboBox<String> comboBox, boolean enabled) {
        comboBox.setDisable(!enabled);
    }

    /**
     * Adjusts the opacity of a label based on its enabled state.
     *
     * @param label   the Label to adjust
     * @param enabled true to set opacity to enabled state, false to set to disabled state
     */
    private void adjustLabelsOpacity(Label label, boolean enabled) {
        final double DISABLED_OPACITY = 0.3;
        final double ENABLED_OPACITY = 1.0;
        label.setOpacity(enabled ? ENABLED_OPACITY : DISABLED_OPACITY);
    }

    /**
     * Sets the enabled state of the location fields.
     *
     * @param enabled true to enable, false to disable
     */
    private void setFieldsEnabled(boolean enabled) {
        countryTextField.setDisable(!enabled);
        cityTextField.setDisable(!enabled);
    }

    /**
     * Handles saving the user's settings.
     */
    @FXML
    public void handleSave() {
        UserSetting preference = new UserSetting(
                userEmail,
                alertsCheckBox.isSelected(),
                sleepCheckBox.isSelected(),
                locationCheckBox.isSelected(),
                startTimeComboBox.getValue(),
                endTimeComboBox.getValue(),
                bedTimeComboBox.getValue(),
                wakeTimeComboBox.getValue(),
                countryTextField.getText(),
                cityTextField.getText(),
                0 // Assuming sliderValue is not relevant here
        );

        UserSetting existingPreference = userSettingDAO.select(userEmail);
        if (existingPreference == null) {
            userSettingDAO.insert(preference);
        } else {
            userSettingDAO.update(preference);
        }
        closePopup();
    }

    /**
     * Handles button click to open the settings popup.
     *
     * @param x the X coordinate of the click
     * @param y the Y coordinate of the click
     */
    @FXML
    protected static void ButtonClick(double x, double y) {
        PopupController.handleOpenPopup(VIEW, x, y, TITLE);
    }
}
