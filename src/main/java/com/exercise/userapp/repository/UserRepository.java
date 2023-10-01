package com.exercise.userapp.repository;

import com.exercise.userapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
