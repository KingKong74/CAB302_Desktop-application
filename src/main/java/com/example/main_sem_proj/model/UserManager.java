package com.example.main_sem_proj.model;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private IUserDAO userDAO;
    public UserManager(IUserDAO userDAO){
        this.userDAO = userDAO;
    }
    public List<User> searchUsers(String query){
        return userDAO.getAllUsers()
                .stream()
                .filter(user -> isUserMatched(user, query))
                .toList();
    }

private boolean isUserMatched(User user, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = user.getEmail()
                + " " + user.getFirstName()
                + " " + user.getLastName()
                + " " + user.getPassword();
        return searchString.toLowerCase().contains(query);
}

    public void addUser(User user){
        userDAO.addUser(user);
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }
}
