package com.tapusd.graphqldemo.graphresolver;

import com.tapusd.graphqldemo.domain.User;
import com.tapusd.graphqldemo.dto.UserDTO;
import com.tapusd.graphqldemo.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> users() {
        return userService.findAll();
    }

    @QueryMapping
    public User user(@Argument Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found by id"));
    }

    @MutationMapping
    public long addUser(@Argument String email,
                        @Argument String name) {
        return userService.save(new UserDTO(email, name));
    }
}
