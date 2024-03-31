package com.mealrecommendationapp.controller;

import com.mealrecommendationapp.dto.RestaurantDto;
import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping(value = "/create")
    public Restaurant createRestaurant(@RequestBody RestaurantDto restaurantDto) throws Exception {
        return restaurantService.createRestaurant(restaurantDto);
    }
    /*get all restaurants*/
    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    /*get restaurant by id*/
    @GetMapping(value = "/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable("id") Integer id) {
        return restaurantService.getRestaurantById(id);
    }
}
