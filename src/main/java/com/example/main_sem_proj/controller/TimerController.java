package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.database.SqliteUserTimerDAO;
import com.example.main_sem_proj.model.users.UserTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import static com.example.main_sem_proj.controller.MainController.userEmail;

public class TimerController {
    private Timeline timeline;
    private int notificationTime = 60; // Predefined duration in seconds

    private final Runnable updateUI;
    NotificationsController notification = new NotificationsController();
    SoundController sound = new SoundController();
    SqliteUserTimerDAO userTimerDAO = new SqliteUserTimerDAO();
    private boolean soundPlayed = false;

    /**
     * Constructor to initialize the TimerController with a Runnable to update the UI.
     * @param updateUI A Runnable that updates the UI, typically provided by the MainController.
     */
    public TimerController(Runnable updateUI) {
        this.updateUI = updateUI;
        this.notificationTime = getUpdatedNotificationTime();
    }

    /**
     * Starts the timer if it is not already running.
     * The timer counts down every second and updates the button label.
     */
    public void startTimer() {
        int resetTime = getUpdatedNotificationTime();
        if (timeline != null) return;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            notificationTime--;
            updateButtonLabel(resetTime);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Set the cycle count to indefinite
        timeline.play();
        updateButtonLabel(resetTime);
    }

    /**
     * Stops the timer if it is running and updates the button label.
     */
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }

    /**
     * Formats the given time in seconds into a string in the format HH:mm:ss.
     * @param seconds The time in seconds to format.
     * @return A string representing the formatted time.
     */
    public String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    /**
     * Updates the stopwatch label to display the current time.
     * If the time runs out, it resets the timer and triggers a notification/notificationSound.
     */
    private void updateButtonLabel(int resetTime) {
        if (notificationTime <= 0 && !soundPlayed){
            sound.notificationSound("big-button");
            soundPlayed = true;
        }
        if (notificationTime <= -1) {
            // Reset the timer
            notificationTime = resetTime;
            soundPlayed = false;
            notification.displayNotification();
        }
        updateUI.run();
    }

    /**
     * Gets the current notification time.
     * @return The current notification time in seconds.
     */
    public int getNotificationTime() {
        return notificationTime;
    }

    /**
     * Gets the current Timeline object.
     * @return The current Timeline object, or null if it is not running.
     */
    public Timeline getTimeline() {
        return timeline;
    }

    public int getUpdatedNotificationTime() {
        UserTimer userTimer = userTimerDAO.select(userEmail);
        if (userTimer != null) {
            this.notificationTime = userTimer.getTimerValue() * 60;
        }
        return notificationTime;
    }
}
