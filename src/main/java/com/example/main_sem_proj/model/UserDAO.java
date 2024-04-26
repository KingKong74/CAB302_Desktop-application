package com.example.main_sem_proj.model;
import java.util.List;

public interface UserDAO {

    void addUser(UserDetails user);

    public Boolean getUser(String email);
    public List<UserDetails> getAllUsers();
}
