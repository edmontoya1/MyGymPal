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

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> createUser(User user) {
        Optional<User> isNewUser = userRepository.findUserByEmail(user.getEmail());

        if (!isNewUser.isEmpty()) {
            throw new IllegalStateException("User email already been used");
        }

        userRepository.save(user);
        return userRepository.findUserByEmail(user.getEmail());
    }

    public Optional<User> getUserByFirstName(String firstName) {
        Optional<User> userByFirstName = userRepository.findUserByFirstName(firstName);

        if (!userByFirstName.isPresent()) {
            throw new IllegalStateException("User not found");
        }

        return userByFirstName;
    }

    public void deleteUserByEmail(String email) {
        Optional<User> deletedUser = userRepository.findUserByEmail(email);

        if (!deletedUser.isPresent()) {
            throw new IllegalStateException("User's email does not exist");
        }

        userRepository.delete(deletedUser.get());
    }
}
