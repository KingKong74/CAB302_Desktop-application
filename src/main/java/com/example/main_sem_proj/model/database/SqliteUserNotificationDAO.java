package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.model.users.UserNotification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteUserNotificationDAO {

    private static Connection connection;

    SqliteCreateTable sqlite = new SqliteCreateTable();

    public SqliteUserNotificationDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS usersNotifications ("
                + "email VARCHAR,"
                + "customSound BOOLEAN,"
                + "customMessage BOOLEAN,"
                + "softAlert BOOLEAN,"
                + "notificationTitle VARCHAR,"
                + "notificationText VARCHAR,"
                + "soundEffect VARCHAR"
                + ")";
        sqlite.createTable(query);
    }

    public void insert(UserNotification userNotification) {
        String sql = "INSERT INTO usersNotifications (email, customSound, customMessage, softAlert," +
                "notificationTitle, notificationText, soundEffect)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userNotification.getEmail());
            pstmt.setBoolean(2, userNotification.getCustomSound());
            pstmt.setBoolean(3, userNotification.getCustomMessage());
            pstmt.setBoolean(4, userNotification.getSoftAlert());
            pstmt.setString(5, userNotification.getNotificationTitle());
            pstmt.setString(6, userNotification.getNotificationText());
            pstmt.setString(7,  userNotification.getSoundEffect());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(UserNotification userNotification) {
        String sql = "UPDATE usersNotifications SET customSound = ?, customMessage = ?, softAlert = ?, " +
                "notificationTitle = ?, notificationText = ?, soundEffect = ? WHERE email = ?";

        try {
            PreparedStatement pstmt  = connection.prepareStatement(sql);
            pstmt.setBoolean(1, userNotification.getCustomSound());
            pstmt.setBoolean(2, userNotification.getCustomMessage());
            pstmt.setBoolean(3, userNotification.getSoftAlert());
            pstmt.setString(4, userNotification.getNotificationTitle());
            pstmt.setString(5, userNotification.getNotificationText());
            pstmt.setString(6, userNotification.getSoundEffect());
            pstmt.setString(7, userNotification.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserNotification select(String email) {
        String sql = "SELECT * FROM usersNotifications WHERE email = ?";
        UserNotification userNotification = null;

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, email);
            ResultSet rs = insertDetails.executeQuery();

            if (rs.next()) {
                userNotification = new UserNotification(
                        rs.getString("email"),
                        rs.getBoolean("customSound"),
                        rs.getBoolean("customMessage"),
                        rs.getBoolean("softAlert"),
                        rs.getString("notificationTitle"),
                        rs.getString("notificationText"),
                        rs.getString("soundEffect")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userNotification;
    }

}