package com.example.main_sem_proj.model;
import java.util.ArrayList;
import java.util.List;
public class MockUserDAO implements UserDAO {
    private List<UserDetails> users = new ArrayList<>();

    @Override
    public void addUser(UserDetails user) {
        users.add(user);
    }
    @Override
    public UserDetails getUser(String email) {
        for (UserDetails user : users) {
            if (user.getEmail() == email) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return new ArrayList<>(users);
    }
}
