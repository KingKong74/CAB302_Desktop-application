package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.controller.NotificationsController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteUserNotificationDAO {

    private static Connection connection;

    SqliteCreateTable sqlite = new SqliteCreateTable();

    public SqliteUserNotificationDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS userNotifications ("
                + "notificationTitle VARCHAR,"
                + "notificationDescription VARCHAR"
                + ")";
        sqlite.createTable(query);
    }

    public void insert(NotificationSetting notificationSetting) {

    }

    public NotificationSetting select(String title) {
        String sql = "SELECT * FROM notificationSettings WHERE title = ?";
        NotificationSetting notificationSetting = null;
        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, title);
            ResultSet rs = insertDetails.executeQuery();

            if (rs.next()) {
                notificationSetting = new NotificationSetting(
                        rs.getString("title"),
                        rs.getString("Description"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return notificationSetting;
    }

}
