package com.example.main_sem_proj.model;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;

public class TimeTextFormatter extends TextFormatter<String> {
    private final ComboBox<String> comboBox;

    public TimeTextFormatter(ComboBox<String> comboBox) {
        super(createFilter());
        this.comboBox = comboBox;
        this.comboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                boolean validFormat = applyFormat();
                if (!validFormat) {
                    comboBox.setValue("");
                }
            }
        });
    }

    public boolean applyFormat() {
        String text = comboBox.getEditor().getText();
        if (text.matches("^([01]?[0-9]|2[0-3])?(:[0-5]?[0-9]?)?$") && text.length() >= 4) {
            return true;
        }
        else {
            return false;
        }
    }

    private static UnaryOperator<Change> createFilter() {
        return change -> {
            String newText = change.getControlNewText();

            // Allow backspace or delete operations
            if (change.isDeleted()) {
                return change;
            }

            // If new text has exactly two digits, clear and reformat as HH:
            if (newText.length() == 2 && !newText.contains(":")) {
                change.setText(newText.substring(1) + ":");
                change.setCaretPosition(3); // Move caret after the colon
                change.setAnchor(3); // Anchor after the colon
                return change;
            }

            // Limit input length to 5 characters
            if (newText.length() > 5) {
                return null;
            }

            if (newText.length() > 4) {
                // If new text matches H:mm format, add a zero at index 1
                if (newText.matches("^[0-9]:[0-5][0-9]$")) {
                    newText = newText.substring(0, 1) + "0" + newText.substring(1);
                    change.setText(newText);
                    change.setCaretPosition(3); // Move caret after the colon
                    change.setAnchor(3); // Anchor after the colon
                    return change;
                }
            }

            // Match HH:mm format
            if (newText.matches("^([01]?[0-9]|2[0-3])?(:[0-5]?[0-9]?)?$")) {
                return change;
            }

            return null;
        };
    }
}

