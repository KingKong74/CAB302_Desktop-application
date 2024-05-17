//package com.example.main_sem_proj.controller;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//
//public class TimerController extends MainController{
//
////    private int notificationTime = 6; // Predefined duration in seconds
//
//    public void startTimer(int notificationTime) {
//        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
//            notificationTime--;
//            updateButtonLabel();
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE); // Set the cycle count to indefinite
//        timeline.play();
//        updateButtonLabel(notificationTime);
//    }
//
//    public void stopTimer(int notificationTime) {
//        if (timeline != null) {
//            timeline.stop();
//            timeline = null;
//            updateButtonLabel(notificationTime);
//        }
//    }
//
//    private String formatTime(int seconds) {
//        int hours = seconds / 3600;
//        int minutes = (seconds % 3600) / 60;
//        int remainingSeconds = seconds % 60;
//        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
//    }
//
//    private void updateButtonLabel(int notificationTime) {
//        if (notificationTime <= -1) {
//            // Reset the timer
//            notification.displayNotification();
//        }
//
//        String timeString = formatTime(notificationTime);
//        timerButton.setText(timeString);
//    }
//}
