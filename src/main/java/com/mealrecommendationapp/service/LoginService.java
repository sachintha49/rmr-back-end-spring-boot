package com.mealrecommendationapp.service;

import com.mealrecommendationapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LoginService {
    public static String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private UserService userService;

    public User getLoggedUser(String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        String[] creds = credentials.split(":", 2);
        return userService.getUserByUserNameAndPassword(creds[0], creds[1]);
    }
}
