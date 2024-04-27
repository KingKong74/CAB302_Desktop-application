package com.example.main_sem_proj.model;

import java.util.List;

public class UserManager {
    private IUserDAO userDAO;

    /**
     * Constructs a new UserManager with the specified IContactDAO.
     *
     * @param userDAO The data access object to be used for user operations.
     */
    public UserManager(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * Searches for contacts that match the given query
     *
     * @param query The query to match contacts.
     * @return A user that matches the query.
     */
    public List<UserDetails> searchUsers(String query){
        return userDAO.getAllUsers();
    }

    /**
     * Adds a new user.
     *
     * @param user The user to add.
     */
    public void addUser(UserDetails user){
        userDAO.addUser(user);
    }
}
