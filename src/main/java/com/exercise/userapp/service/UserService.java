package com.exercise.userapp.service;

import com.exercise.userapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Integer userId);

    User createUser(User user);

    User updateUser(Integer userId, User user);

    void deleteUser(Integer userId);
}
