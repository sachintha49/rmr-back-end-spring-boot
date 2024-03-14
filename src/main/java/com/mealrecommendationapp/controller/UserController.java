package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*User Registration*/
    @PostMapping(value = "/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /*Get User By Id*/
    @GetMapping(value = "/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
}
