package com.exercise.userapp.service;

import com.exercise.userapp.model.User;
import com.exercise.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return StreamSupport
                .stream(
                        userRepository.findAll().spliterator(),
                        false)
                .toList();
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer userId, User user) {
        if (userRepository.existsById(userId)) {
            user.setId(userId);
            return userRepository.save(user);
        }
        return null;
    }


    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
