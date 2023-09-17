package com.tapusd.graphqldemo.controller;

import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.UserDTO;
import com.tapusd.graphqldemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long saveUser(@RequestBody UserDTO dto) {
        return userService.save(dto);
    }

    @GetMapping
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with provided id!"));
    }
}
