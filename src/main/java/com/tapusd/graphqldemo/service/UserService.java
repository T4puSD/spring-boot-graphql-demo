package com.tapusd.graphqldemo.service;

import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long save(UserDTO dto);
    Optional<User> findById(long id);
    List<User> findAll();
}
