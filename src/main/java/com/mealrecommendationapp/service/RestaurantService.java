package com.mealrecommendationapp.service;

import com.mealrecommendationapp.dto.RestaurantDto;
import com.mealrecommendationapp.enums.UserRole;
import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.model.User;
import com.mealrecommendationapp.repository.RestaurantMenuItemRepository;
import com.mealrecommendationapp.repository.RestaurantRepository;
import com.mealrecommendationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Transactional(readOnly = false)
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant createRestaurant(RestaurantDto restaurant) throws Exception{
        User newUser = User.builder()
                .firstName(restaurant.getFirstName())
                .lastName(restaurant.getLastName())
                .userName(restaurant.getUserName())
                .role(UserRole.REST_ADMIN.toString())
                .password(restaurant.getPassword())
                .build();

        userRepository.save(newUser);

        Restaurant newRestaurant = Restaurant.builder()
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .email(restaurant.getEmail())
                .avgMealRate(restaurant.getAvgMealRate())
                .openingHours(restaurant.getOpeningHours())
                .phone(restaurant.getPhone())
                .priceRange(restaurant.getPriceRange())
                .website(restaurant.getWebsite())
                .cuisines(restaurant.getCuisines())
                .facilities(restaurant.getFacilities())
                .lat(restaurant.getLat())
                .lng(restaurant.getLng())
                .user(newUser)
                .build();

        return restaurantRepository.save(newRestaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }

    public double updateMenuItemAverage(Integer id) {
        double avg = restaurantMenuItemRepository.getAverageRatingByRestaurantId(id);
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        restaurant.get().setAvgMealRate(avg);
        restaurantRepository.save(restaurant.get());
        return avg;
    }
}
