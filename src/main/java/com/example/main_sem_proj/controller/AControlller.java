package com.example.main_sem_proj.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AControlller {
    @FXML
    private TextArea asciiTextArea;

    public void initialize() {
        asciiTextArea.setText(
                "░░░░▄▄▄▄▀▀▀▀▀▀▀▀▄▄▄▄▄▄\n" +
                "░░░░█░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▀▀▄\n" +
                "░░░█░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░░█\n" +
                "░░█░░░░░░▄██▀▄▄░░░░░▄▄▄░░░█\n" +
                "░▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░░█\n" +
                "█▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒▒█\n" +
                "█▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀▄▒█\n" +
                "░█▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░░█\n" +
                "░░█░░▀▄▀█▄▄░█▀▀▀▄▄▄▄▀▀█▀██░█\n" +
                "░░░█░░██░░▀█▄▄▄█▄▄█▄████░█\n" +
                "░░░░█░░░▀▀▄░█░░░█░███████░█\n" +
                "░░░░░▀▄░░░▀▀▄▄▄█▄█▄█▄█▄▀░░█\n" +
                "░░░░░░░▀▄▄░▒▒▒▒░░░░░░░░░░█\n" +
                "░░░░░░░░░░▀▀▄▄░▒▒▒▒▒▒▒▒▒▒░█\n" +
                "░░░░░░░░░░░░░░▀▄▄▄▄▄░░░░░█");
    }
}
