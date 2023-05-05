package com.eduardomontoya.service;

import com.eduardomontoya.model.User;
import com.eduardomontoya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void createUser(User user) {
        User newUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        userRepository.save(newUser);
    }

    public Optional<User> getUserByFirstName(String firstName) {
        Optional<User> userByFirstName = userRepository.findUserByFirstName(firstName);

        if (!userByFirstName.isPresent()) {
            throw new IllegalStateException("User not found");
        }

        return userByFirstName;
    }
}
