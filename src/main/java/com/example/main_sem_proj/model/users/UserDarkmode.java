package com.example.main_sem_proj.model.users;

public class UserDarkmode {
    private final String email;
    private final Boolean darkMode;

    public UserDarkmode(String email, Boolean darkMode) {
        this.email = email;
        this.darkMode = darkMode;
    }

    public String getEmail() {return email;}
    public Boolean getDarkMode(){return darkMode;}
}
