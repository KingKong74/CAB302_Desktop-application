package com.example.main_sem_proj.model.database;

import java.sql.Connection;

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
}
