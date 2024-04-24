package com.example.main_sem_proj.model;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private UserDAO userDAO;
    public UserManager(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    public List<UserDetails> searchUsers(String query){
        return userDAO.getAllUsers();
    }
    public void addUser(UserDetails user){
        userDAO.addUser(user);
    }
}
