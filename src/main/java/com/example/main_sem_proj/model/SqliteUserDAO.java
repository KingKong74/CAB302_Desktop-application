package com.example.main_sem_proj.model;

import java.sql.*;

public class SqliteUserDAO implements IUserDAO {
    private static Connection connection;

    /**
     * Constructs a new SQliteContactDAO with a connection to the SQLite database,
     * and creates the users table if it does not exist.
     */
    public SqliteUserDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a table in DB
     */
    public void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query =
                    "CREATE TABLE IF NOT EXISTS users ("
                    + "email VARCHAR PRIMARY KEY,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
            System.out.println("line reached");
        } catch (Exception e) {
            System.err.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database.
     *
     * @param userDetails The UserDetails object representing the user to be added.
     */
    @Override
    public void addUser(UserDetails userDetails) {
        try {
            PreparedStatement insertDetails = connection.prepareStatement(
                    "INSERT INTO users (email, firstName, lastName, password) VALUES (?, ?, ?, ?)"
            );
            insertDetails.setString(1, userDetails.getEmail());
            insertDetails.setString(2, userDetails.getFirstName());
            insertDetails.setString(3, userDetails.getLastName());
            insertDetails.setString(4, userDetails.getPassword());
            insertDetails.execute();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves a user from the database based on the provided email and password.
     *
     * @param email The email address of the user to retrieve.
     * @param password The password of the user to retrieve.
     * @return A UserDetails object representing the retrieved user if found, or null if the user is not found or an error occurs.
     */
    @Override
    public UserDetails getUser(String email, String password) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            getUser.setString(1, email);
            getUser.setString(2, password);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new UserDetails(email, firstName, lastName, password);
            } else {
                // User with the provided email and password not found
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

        /**
         * Handles closing of DB
         */
        public void close () {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
}
