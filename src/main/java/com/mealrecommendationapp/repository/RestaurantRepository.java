package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant findByUser(User user);
}
