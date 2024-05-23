package com.example.main_sem_proj.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundController {
    public void playSound(InputStream inputStream) {
        try {
            File tempFile = File.createTempFile("temp", ".mp3");
            tempFile.deleteOnExit();
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();

            Media sound = new Media(tempFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public void notificationSound(String soundName){
        try {
            // Load the MP3 file as an InputStream
            InputStream inputStream = getClass().getResourceAsStream("/sounds/" + soundName + ".mp3");

            playSound(inputStream);

            // Close the InputStream when done
            assert inputStream != null;
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Error loading MP3 file: " + e.getMessage());
        }
    }
}
