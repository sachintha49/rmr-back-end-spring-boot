package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.RestaurantMenuItemRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface RestaurantMenuItemRateRepository extends Repository<RestaurantMenuItemRate, Integer> {

    RestaurantMenuItemRate findByRestaurantMenuItemIdAndUserId(int menuItemId, int userID);

    RestaurantMenuItemRate save(RestaurantMenuItemRate restaurantMenuItemRate);

    @Query(
            nativeQuery = true,
            value = "select avg(rate) from restaurant_menu_item_rate where menu_item_id = ?1"
    )
    double getRestaurantMenuItemAverageRate(int menuItemId);
}
