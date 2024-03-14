package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    final
    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/add")
    public Restaurant addRestaurants(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }
}
