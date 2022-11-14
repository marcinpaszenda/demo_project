package com.example.demoproject.service;

import com.example.demoproject.domain.User;
import com.example.demoproject.exception.UserNotFoundException;
import com.example.demoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(final User user) throws UserNotFoundException {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }
}
