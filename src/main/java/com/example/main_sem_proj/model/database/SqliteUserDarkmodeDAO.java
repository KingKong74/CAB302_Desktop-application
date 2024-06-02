package com.example.main_sem_proj.model.database;

import com.example.main_sem_proj.model.users.UserDarkmode;
import com.example.main_sem_proj.model.users.UserSetting;

import java.sql.*;

public class SqliteUserDarkmodeDAO {
    private static Connection connection;
    SqliteCreateTable sqlite = new SqliteCreateTable();

    public SqliteUserDarkmodeDAO(){
        connection = SqliteConnection.getInstance();
        String query = "CREATE TABLE IF NOT EXISTS usersDarkMode ("
                + "email VARCHAR PRIMARY KEY NOT NULL UNIQUE,"
                + "darkmode BOOLEAN)";
        sqlite.createTable(query);
    }

    public void insert(UserDarkmode userDarkmode){
        String sql ="INSERT INTO usersDarkmode (email, darkmode)";

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,userDarkmode.getEmail());
            pstmt.setBoolean(2,userDarkmode.getDarkMode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(UserDarkmode userDarkmode){
        String sql = "UPDATE usersDarkmode SET darkmode = ? WHERE email = ?";

        try{
            PreparedStatement pstmt  = connection.prepareStatement(sql);
            pstmt.setBoolean(1, userDarkmode.getDarkMode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public UserDarkmode select(String email){
        String sql = "SELECT email, darkmode FROM usersDarkmode WHERE email = ?";
        UserDarkmode userDarkmode = null;

        try {
            PreparedStatement insertDetails = connection.prepareStatement(sql);
            insertDetails.setString(1, email);
            ResultSet rs = insertDetails.executeQuery();

            if(rs.next()){
                userDarkmode = new UserDarkmode(
                        rs.getString("email"),
                        rs.getBoolean("darkmode")
                );
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userDarkmode;
    }
}
