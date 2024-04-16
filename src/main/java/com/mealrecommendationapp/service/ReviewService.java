package com.mealrecommendationapp.service;

import com.mealrecommendationapp.model.RestaurantMenuItemReview;
import com.mealrecommendationapp.model.RestaurantReview;
import com.mealrecommendationapp.repository.RestaurantMenuItemReviewRepository;
import com.mealrecommendationapp.repository.RestaurantReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private RestaurantReviewRepository restaurantReviewRepository;
    @Autowired
    private RestaurantMenuItemReviewRepository restaurantMenuItemReviewRepository;

    public RestaurantReview saveRestaurantReview(RestaurantReview restaurantReview) {
        return restaurantReviewRepository.save(restaurantReview);
    }

    public List<RestaurantReview> getRestaurantReviewsByRestaurantId(int restaurantId) {
        return restaurantReviewRepository.findAllByRestaurantIdOrderByIdDesc(restaurantId);
    }

    public List<RestaurantMenuItemReview> getRestaurantMenuItemReviews(int restaurantMenuItemId) {
        return restaurantMenuItemReviewRepository.findByRestaurantMenuItemIdOrderByIdDesc(restaurantMenuItemId);
    }

}
