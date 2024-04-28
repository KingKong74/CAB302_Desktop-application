package com.example.main_sem_proj.model;

/**
 * Interface for managing user data access in the database.
 */
public interface IUserDAO {

    /**
     * Adds a new user to the database.
     *
     * @param user The user details to be added.
     */
    void addUser(UserDetails user);

    /**
     * Checks if a user with the specified email exists in the database.
     *
     * @param email The email address of the user to check.
     * @return First/Last name if the user exists in the database, otherwise null.
     */
    UserDetails getUser(String email, String password);
}

