package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.dto.MenuItemDetailsDto;
import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.model.RestaurantMenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantMenuItemRepository extends CrudRepository<RestaurantMenuItem, Integer> {

    List<RestaurantMenuItem> findByRestaurant(Restaurant restaurant);

    @Query("SELECT mi.id AS menu_item_id, mi.description AS menu_item_desc, mi.name, mi.id, " +
            "rmi.largePrice AS large_price, rmi.mediumPrice AS medium_price, rmi.smallPrice AS small_price, rmi.restaurant " +
            "FROM MenuItem mi " +
            "INNER JOIN RestaurantMenuItem rmi " +
            "ON rmi.menuItem.id = mi.id " +
            "WHERE rmi.restaurant.id = :restaurantId")
    List<MenuItemDetailsDto[]> findCustomQueryByRestaurantId(Integer restaurantId);

    @Query(
            nativeQuery = true,
            value = "SELECT AVG(avg_final) FROM restaurant_menu_item WHERE restaurant_id = ?1 AND avg_final IS NOT NULL"
    )
    double getAverageRatingByRestaurantId(Integer restaurantId);
}
