package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.model.database.SqliteConnection;
import com.example.main_sem_proj.model.users.IUserDAO;
import com.example.main_sem_proj.model.users.User;

import java.sql.*;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private static Connection connection;

    SqliteCreateTable sqlite = new SqliteCreateTable();

    /**
     * Constructs a new SQliteContactDAO with a connection to the SQLite database,
     * and creates the users table if it does not exist.
     */
    public SqliteUserDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS users ("
                + "email VARCHAR PRIMARY KEY,"
                + "firstName VARCHAR NOT NULL,"
                + "lastName VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL"
                + ")";
        sqlite.createTable(query);
    }

    /**
     * Adds a new user to the database.
     *
     * @param userDetails The UserDetails object representing the user to be added.
     */
    @Override
    public void addUser(User userDetails) {
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
    public User getUser(String email, String password) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            getUser.setString(1, email);
            getUser.setString(2, password);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(email, firstName, lastName, password);
            } else {
                // User with the provided email and password not found
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    public boolean CheckEmailTaken(String email) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
            getUser.setString(1, email);

            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return true;
               }
             else {
                // User with the provided email and password not found
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return true;
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
