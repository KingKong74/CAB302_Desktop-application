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
                       String city, int sliderValue ){
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

    public void setAlertSchedule(Boolean alertSchedule) {
        this.alertSchedule = alertSchedule;
    }

    public Boolean getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(Boolean sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public Boolean getLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBedTime() {
        return bedTime;
    }

    public void setBedTime(String bedTime) {
        this.bedTime = bedTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getSliderValue() {
        return sliderValue;
    }

    public void setSliderValue(int sliderValue) {
        this.sliderValue = sliderValue;
    }

}
