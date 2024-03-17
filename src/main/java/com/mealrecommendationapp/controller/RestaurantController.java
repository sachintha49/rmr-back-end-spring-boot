package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.dto.RestaurantDto;
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

    @PostMapping(value = "/add")
    public Restaurant addRestaurants(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }
/*TODO: on development*/
    @PostMapping(value = "/create")
    public Restaurant createRestaurant(@RequestBody RestaurantDto restaurantDto) throws Exception {
        return restaurantService.createRestaurant(restaurantDto);
    }
}
