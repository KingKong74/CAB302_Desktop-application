package com.example.main_sem_proj.model;
import java.util.List;

/**
 * Interface for the Users
 */
public interface IUserDAO {

    /**
     * Adds a new user to the database
     * @param user
     */
    public void addUser(UserDetails user);

    /**
     * Gets user for DB. True if exists else false.
     * @param email
     * @return
     */
    public Boolean getUser(String email);

    /**
     * idk yet
     * @return
     */
    public List<UserDetails> getAllUsers();
}
