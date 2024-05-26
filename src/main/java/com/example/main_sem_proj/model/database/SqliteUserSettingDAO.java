package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.model.users.UserSetting;

import java.sql.*;

public class SqliteUserSettingDAO {

    private static Connection connection;
    SqliteCreateTable sqlite = new SqliteCreateTable();

    public SqliteUserSettingDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS usersSettings ("
                + "email VARCHAR PRIMARY KEY NOT NULL UNIQUE,"
                + "alertSchedule BOOLEAN,"
                + "sleepSchedule BOOLEAN,"
                + "location BOOLEAN,"
                + "startTIme VARCHAR,"
                + "endTime VARCHAR,"
                + "bedTime VARCHAR,"
                + "wakeTime VARCHAR,"
                + "country VARCHAR,"
                + "city VARCHAR,"
                + "sliderValue INTEGER"
                + ")";
        sqlite.createTable(query);
    }

    public void insert(UserSetting userSetting) {
        String sql = "INSERT INTO usersSettings (email, alertSchedule, sleepSchedule, location, " +
                "startTime, endTime, bedTime, wakeTime, country, city, sliderValue) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userSetting.getEmail());
            pstmt.setBoolean(2, userSetting.getAlertSchedule() != null ? userSetting.getAlertSchedule() : false);
            pstmt.setBoolean(3, userSetting.getSleepSchedule()  != null ? userSetting.getAlertSchedule() : false);
            pstmt.setBoolean(4, userSetting.getLocation() != null ? userSetting.getAlertSchedule() : false);
            pstmt.setString(5, userSetting.getStartTime());
            pstmt.setString(6, userSetting.getEndTime());
            pstmt.setString(7, userSetting.getBedTime());
            pstmt.setString(8, userSetting.getWakeTime());
            pstmt.setString(9, userSetting.getCountry());
            pstmt.setString(10, userSetting.getCity());
            pstmt.setInt(11, userSetting.getSliderValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(UserSetting userSetting) {
        String sql = "UPDATE usersSettings SET alertSchedule = ?, sleepSchedule = ?, location = ?," +
                " startTime = ?, endTime = ?, bedTime = ?, wakeTime = ?, country = ?, city = ?, " +
                "sliderValue = ? WHERE email = ?";

        try {
            PreparedStatement pstmt  = connection.prepareStatement(sql);
            pstmt.setBoolean(1, userSetting.getAlertSchedule());
            pstmt.setBoolean(2, userSetting.getSleepSchedule());
            pstmt.setBoolean(3, userSetting.getLocation());
            pstmt.setString(4, userSetting.getStartTime());
            pstmt.setString(5, userSetting.getEndTime());
            pstmt.setString(6, userSetting.getBedTime());
            pstmt.setString(7, userSetting.getWakeTime());
            pstmt.setString(8, userSetting.getCountry());
            pstmt.setString(9, userSetting.getCity());
            pstmt.setInt(10, userSetting.getSliderValue());
            pstmt.setString(11, userSetting.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserSetting select(String email) {
        String sql = "SELECT * FROM usersSettings WHERE email = ?";
        UserSetting userSetting = null;

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, email);
            ResultSet rs = insertDetails.executeQuery();

            if (rs.next()) {
                userSetting = new UserSetting(
                        rs.getString("email"),
                        rs.getBoolean("alertSchedule"),
                        rs.getBoolean("sleepSchedule"),
                        rs.getBoolean("location"),
                        rs.getString("startTime"),
                        rs.getString("endTime"),
                        rs.getString("bedTime"),
                        rs.getString("wakeTime"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getInt("sliderValue")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userSetting;
    }
}
