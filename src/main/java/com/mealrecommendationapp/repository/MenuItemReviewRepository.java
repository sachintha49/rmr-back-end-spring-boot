package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.RestaurantMenuItemRate;
import com.mealrecommendationapp.model.RestaurantMenuItemReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface MenuItemReviewRepository extends Repository<RestaurantMenuItemReview, Integer> {

    RestaurantMenuItemReview findByRestaurantMenuItemIdAndUserId(int menuItemId, int userID);

    RestaurantMenuItemReview save(RestaurantMenuItemReview restaurantMenuItemReview);

    @Query(
            nativeQuery = true,
            value = "select avg(rate) from restaurant_menu_item_review where menu_item_id = ?1"
    )
    double getRestaurantMenuItemAverageRate(int menuItemId);

}
