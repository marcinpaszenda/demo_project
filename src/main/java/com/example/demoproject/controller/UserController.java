package com.example.demoproject.controller;

import com.example.demoproject.domain.User;
import com.example.demoproject.domain.dto.UserDto;
import com.example.demoproject.exception.UserNotFoundException;
import com.example.demoproject.mapper.UserMapper;
import com.example.demoproject.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @GetMapping(value = "{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.findUserById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        return userService.saveUser(user);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userService.updateUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
