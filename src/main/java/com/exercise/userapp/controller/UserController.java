package com.exercise.userapp.controller;

import com.exercise.userapp.model.User;
import com.exercise.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user;
        if (userService.getUserById(id).isPresent()) {
            user = userService.getUserById(id).get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User newUserRequest,
                                           UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.createUser(newUserRequest);
        URI userLocation = uriComponentsBuilder
                .path("/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(userLocation).build();

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,
                                           @RequestBody User userUpdate) {

        User updatedUser = userService.updateUser(id, userUpdate);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
    ) {
        List<User> users;

        if (firstName != null && lastName != null) {
            users = userService.searchUsersByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            users = userService.searchUsersByFirstName(firstName);
        } else if (lastName != null) {
            users = userService.searchUsersByLastName(lastName);
        } else {
            users = userService.getAllUsers();
        }

        return ResponseEntity.ok(users);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importUsers(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    User user = new User();
                    user.setFirstName(data[0]);
                    user.setLastName(data[1]);
                    user.setEmail(data[2]);
                    user.setAddress(data[3]);

                    userService.createUser(user);
                }
            }

            return ResponseEntity.ok("Dati CSV importati con successo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
