package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.model.users.UserTimer;

import java.sql.*;

public class SqliteUserTimerDAO {

    private static Connection connection;

    SqliteCreateTable sqlite = new SqliteCreateTable();

    public SqliteUserTimerDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS usersTimer ("
                + "email VARCHAR PRIMARY KEY,"
                + "timerValue INTEGER"
                + ")";
        sqlite.createTable(query);
    }

    public void insert(UserTimer preference) {
        String sql = "INSERT INTO usersTimer(email, timerValue) VALUES(?,?)";

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, preference.getEmail());
            insertDetails.setInt(2, preference.getTimerValue());
            insertDetails.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(UserTimer preference) {
        String sql = "UPDATE usersTimer SET timerValue = ? WHERE email = ?";

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setInt(1, preference.getTimerValue());
            insertDetails.setString(2, preference.getEmail());
            insertDetails.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserTimer select(String email) {
        String sql = "SELECT email, timerValue FROM usersTimer WHERE email = ?";
        UserTimer preference = null;

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, email);
            ResultSet rs = insertDetails.executeQuery();

            if (rs.next()) {
                String userEmail = rs.getString("email");
                int timerValue = rs.getInt("timerValue");
                preference = new UserTimer(userEmail, timerValue);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return preference;
    }
}
