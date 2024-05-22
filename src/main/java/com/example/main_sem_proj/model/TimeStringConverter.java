package com.example.main_sem_proj.model;

import javafx.util.StringConverter;

public class TimeStringConverter extends StringConverter<String> {
    @Override
    public String toString(String object) {
        return object;
    }

    @Override
    public String fromString(String string) {
        if (string.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            return string;
        }
        return null;
    }
}


