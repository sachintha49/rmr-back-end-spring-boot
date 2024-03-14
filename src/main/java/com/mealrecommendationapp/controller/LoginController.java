package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.exception.InvalidLoginException;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;


import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<User> login(HttpServletRequest req) throws InvalidLoginException {
        String authorization = req.getHeader(LoginService.HEADER_AUTHORIZATION);
        User user = loginService.getLoggedUser(authorization);
        if (user == null){
            throw new InvalidLoginException("Invalid Credentials");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user_data")
    public Map<String, String> getData(){
        Map<String, String> resource = new HashMap<String, String>();
        resource.put("user", "sample user details");
        return resource;
    }
}
