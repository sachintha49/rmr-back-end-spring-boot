package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.RestaurantMenuItemRecommend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface RestaurantMenuItemRecommendRepository extends Repository<RestaurantMenuItemRecommend, Integer> {

    RestaurantMenuItemRecommend save(RestaurantMenuItemRecommend restaurantMenuItemRate);

    RestaurantMenuItemRecommend findByRestaurantMenuItemIdAndUserId(int menuItemId, int userID);

    @Query(
            nativeQuery = true,
            value = "select (" +
                    "sum(case when recommend=true then 1 else 0 end)/" +
                    "   (sum(case when recommend=true then 1 else 0 end) + sum(case when recommend=false then 1 else 0 end))" +
                    ") * 5 as avg " +
                    "from restaurant_menu_item_recommend where menu_item_id = ?1"
    )
    double getRestaurantMenuItemAverageRecommend(int menuItemId);
}
