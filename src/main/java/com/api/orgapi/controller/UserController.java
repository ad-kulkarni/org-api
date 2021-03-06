package com.api.orgapi.controller;

import com.api.orgapi.model.User;
import com.api.orgapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/user")
    public void createUser(User user) {
        userService.createUser(user);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
