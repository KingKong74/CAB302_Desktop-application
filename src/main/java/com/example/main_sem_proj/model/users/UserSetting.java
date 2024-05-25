package com.example.main_sem_proj.model.users;

public class UserSetting {
    private final String email;
    private Boolean alertSchedule;
    private Boolean sleepSchedule;
    private Boolean location;
    private String startTime;
    private String endTime;
    private String bedTime;
    private String wakeTime;
    private String country;
    private String city;
    private int sliderValue;

    public UserSetting(String email, Boolean alertSchedule, Boolean sleepSchedule, Boolean location,
                       String startTime, String endTime, String bedTime, String wakeTime, String country,
                       String city, int sliderValue) {
        this.email = email;
        this.alertSchedule = alertSchedule;
        this.sleepSchedule = sleepSchedule;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.country = country;
        this.city = city;
        this.sliderValue = sliderValue;
    }

    public String getEmail(){
        return email;
    }

    public Boolean getAlertSchedule() {
        return alertSchedule;
    }

    public Boolean getSleepSchedule() {
        return sleepSchedule;
    }

    public Boolean getLocation() {
        return location;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getBedTime() {
        return bedTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Integer getSliderValue() {
        return sliderValue;
    }
}
