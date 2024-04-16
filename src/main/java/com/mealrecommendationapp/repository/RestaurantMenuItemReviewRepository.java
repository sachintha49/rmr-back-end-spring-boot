package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.RestaurantMenuItemReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RestaurantMenuItemReviewRepository extends Repository<RestaurantMenuItemReview, Integer> {

    /**
     * Save restaurant menu item review.
     *
     * @param restaurantMenuItemReview The restaurant menu item review
     * @return The saved {@link RestaurantMenuItemReview}
     */
    RestaurantMenuItemReview save(RestaurantMenuItemReview restaurantMenuItemReview);
    /**
     * Find menu item review by id.
     *
     * @param menuItemReviewId The menu item review id
     * @return The {@link RestaurantMenuItemReview}
     */
    RestaurantMenuItemReview findById(int menuItemReviewId);

    /**
     * Find review by restaurant menu item id.
     *
     * @param restaurantMenuItem The restaurant menu item id
     * @return The matched restaurant menu item reviews
     */
    List<RestaurantMenuItemReview> findByRestaurantMenuItemIdOrderByIdDesc(int restaurantMenuItem);

    @Query(
            nativeQuery = true,
            value = "select avg(rate) from RestaurantMenuItemReview where menu_item_id = ?1"
    )
    double getRestaurantMenuItemAverageReview(int menuItemId);

}
