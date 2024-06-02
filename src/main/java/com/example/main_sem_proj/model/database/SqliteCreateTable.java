package com.example.main_sem_proj.model.database;

import java.sql.Connection;
import java.sql.Statement;

public class SqliteCreateTable {

    /**
     * Creates a table in DB
     */
    public void createTable(String query) {
        Connection connection = SqliteConnection.getInstance();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }
}
