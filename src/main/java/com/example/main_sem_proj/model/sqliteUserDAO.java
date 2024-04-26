package com.example.main_sem_proj.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class sqliteUserDAO implements UserDAO {
    private Connection connection;
    public sqliteUserDAO(){
        connection = SqliteConnection.getInstance();

    }

    private void createTable() {

        try {
            Statement statement = connection.createStatement();
            String query =
                    "CREATE TABLE IF NOT EXISTS users (" + "email VARCHAR PRIMARY KEY,"
                    + "password VARCHAR NOT NULL"
                    +")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addUser(UserDetails user) {
        String email = user.getEmail();
        String password = user.getPassword();
        try {
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO users (email, password) VALUES "
                    + "('" + email + "', '" + password + "')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean getUser(String email) {

        try {
            Statement getStatement = connection.createStatement();
            String getQuery = "SELECT email FROM users "
                    + "WHERE email = " + "'" + email + "'" +"')";
            //tries to get the email form database
            ResultSet SqlEmail = getStatement.executeQuery(getQuery);
            try { while (SqlEmail.next()) {
                //turns the email into a string that can be read (hopefully)
                String ResultEmail = SqlEmail.getString(1);
            }
            } catch (Exception e){
                //if it can't do that (cause there is no string) should mean that email is not in the database, therefore it should return false
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //if it passes the error traps it means that there should be an email in the database
        return true;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return List.of();
    }
}
