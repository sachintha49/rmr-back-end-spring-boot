package com.mealrecommendationapp.service;

import com.mealrecommendationapp.dto.UserLoginRequest;
import com.mealrecommendationapp.dto.UserLoginResponse;
import com.mealrecommendationapp.exception.InvalidLoginException;
import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.model.UserLogin;
import com.mealrecommendationapp.repository.RestaurantRepository;
import com.mealrecommendationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    public static String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public UserLoginResponse userLogin(UserLoginRequest userLogin) {

        User user = userRepository.findByUserName(userLogin.getUsername());

        if(!user.getPassword().equals(userLogin.getPassword()) && !user.getUserName().equals(userLogin.getUsername())){
            throw new InvalidLoginException("Invalid username and password!");
        }

        UserLoginResponse response = new UserLoginResponse();

        response.setRole(user.getRole());
        response.setUsername(user.getUserName());

        Optional<Restaurant> restaurant = Optional.ofNullable(restaurantRepository.findByUser(user));
        if (restaurant.isPresent()){
            response.setRestaurantId(restaurant.get().getId().toString());
        }


        return response;
    }
}
