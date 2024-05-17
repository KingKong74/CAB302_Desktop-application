package com.example.main_sem_proj.model.notifications;

public class MockDisplayComponent implements DisplayComponent {
    private boolean popupDisplayed = false;
    private String notificationMessage = "";

    @Override
    public void showPopup() {
        popupDisplayed = true;
    }

    @Override
    public void hidePopup() {
        popupDisplayed = false;
    }

    @Override
    public void updateNotification(String message) {
        notificationMessage = message;
    }

    // Additional methods for testing purposes
    public boolean isPopupDisplayed() {
        return popupDisplayed;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
