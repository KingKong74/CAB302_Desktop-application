package com.example.main_sem_proj.model.Notifications;

public interface DisplayComponent {
    void showPopup();
    void hidePopup();
    void updateNotification(String message);
    // Other methods as needed
}