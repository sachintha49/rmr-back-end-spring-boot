package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.dto.UserLoginRequest;
import com.mealrecommendationapp.dto.UserLoginResponse;
import com.mealrecommendationapp.exception.InvalidLoginException;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.model.UserLogin;
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
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLogin)  {
        UserLoginResponse response = loginService.userLogin(userLogin);
        return ResponseEntity.ok(response);
    }
}
