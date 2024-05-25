package com.example.main_sem_proj.model.users;

public class UserNotification {
    private final String title;
    private final String description;

    public UserNotification(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getDescription() {return description;}

    public String getTitle() {return title;}
}

