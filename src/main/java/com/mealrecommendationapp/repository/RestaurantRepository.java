package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
