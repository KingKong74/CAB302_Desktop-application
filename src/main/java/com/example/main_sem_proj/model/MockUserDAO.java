package com.example.main_sem_proj.model;
import java.util.ArrayList;
import java.util.List;
public class MockUserDAO implements IUserDAO {
    private List<UserDetails> users = new ArrayList<>();

    @Override
    public void addUser(UserDetails user) {
        users.add(user);
    }
    @Override
    public Boolean getUser(String email) {
        for (UserDetails user : users) {
            if (user.getEmail() == email) {
                return true;
            }
        }
        return null;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return new ArrayList<>(users);
    }
}
