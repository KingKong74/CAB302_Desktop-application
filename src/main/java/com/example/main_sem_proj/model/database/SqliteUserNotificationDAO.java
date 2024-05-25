package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.controller.NotificationsController;
import com.example.main_sem_proj.model.users.UserNotification;
import com.example.main_sem_proj.model.users.UserSetting;

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

    public void insert(UserNotification notificationSetting) {
        String sql = "INSERT INTO usersNotifications (title, description)";

        try{
            PreparedStatement test = connection.prepareStatement(sql);
            test.setString(1, notificationSetting.getTitle());
            test.setString(1, notificationSetting.getDescription());
            test.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserNotification select(String email) {
        String sql = "SELECT * FROM usersSettings WHERE title = ?";
        UserNotification userNotification = null;

        try {
            PreparedStatement notifdetails = connection.prepareStatement(sql);
            notifdetails.setString(1, email);
            ResultSet rs = notifdetails.executeQuery();

            if (rs.next()) {
                userNotification = new UserNotification(
                        rs.getString("title"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userNotification;
    }

}
