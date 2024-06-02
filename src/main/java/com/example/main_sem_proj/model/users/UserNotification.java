package com.example.main_sem_proj.model.users;

public class UserNotification {
    private final String email;
    private final Boolean customSound;
    private final Boolean customMessage;
    private final Boolean softAlert;
    private final String notificationTitle;
    private final String notificationText;
    private final String soundEffect;

    public UserNotification(String email, Boolean customSound, Boolean customMessage, Boolean softAlert, String notificationTitle, String notificationText, String soundEffect) {
        this.email = email;
        this.customSound = customSound;
        this.customMessage = customMessage;
        this.softAlert = softAlert;
        this.notificationTitle = notificationTitle;
        this.notificationText = notificationText;
        this.soundEffect = soundEffect;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getCustomSound() {
        return customSound;
    }

    public Boolean getCustomMessage() {
        return customMessage;
    }

    public Boolean getSoftAlert() {
        return softAlert;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public String getSoundEffect() {
        return soundEffect;
    }
}
