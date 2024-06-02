package com.example.main_sem_proj.model.users;

public class UserTimer {
    private final String email;
    private int timerValue;

    public UserTimer(String email, int timerValue) {
        this.email = email;
        this.timerValue = timerValue;
    }

    public String getEmail() {
        return email;
    }

    public int getTimerValue() {
        return timerValue;
    }

}
