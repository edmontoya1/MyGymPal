package com.eduardomontoya.controller;

import com.eduardomontoya.model.User;
import com.eduardomontoya.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable(value = "userId") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{firstName}")
    public Optional<User> getUserByFirstName(@PathVariable(name = "firstName") String firstName) {
        return userService.getUserByFirstName(firstName);
    }

    @PostMapping
    public Optional<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable(value = "userId") Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/delete/{userEmail}")
    public void deleteUserByEmail(@PathVariable(value = "userEmail") String email) {
        System.out.println(email);
        userService.deleteUserByEmail(email);
    }
}
