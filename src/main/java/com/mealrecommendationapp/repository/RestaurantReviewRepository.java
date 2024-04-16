package com.mealrecommendationapp.repository;

import com.mealrecommendationapp.model.RestaurantReview;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RestaurantReviewRepository extends Repository<RestaurantReview, Integer> {

    /**
     * Save restaurant review.
     *
     * @param restaurantReview The restaurant review
     * @return The saved restaurant review
     */
    RestaurantReview save(RestaurantReview restaurantReview);

    /**
     * Get all restaurant reviews by restaurant id.
     *
     * @param restaurantId The restaurant id
     * @return The matched restaurant reviews
     */
    List<RestaurantReview> findAllByRestaurantIdOrderByIdDesc(int restaurantId);

}
