package com.exercise.userapp.repository;

import com.exercise.userapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);
}
