package com.example.main_sem_proj.model.users;

import java.util.ArrayList;
import java.util.List;
public class MockUserDAO implements IUserDAO {
    public final ArrayList<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }
    @Override
    public User getUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail() == email) {
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

}
