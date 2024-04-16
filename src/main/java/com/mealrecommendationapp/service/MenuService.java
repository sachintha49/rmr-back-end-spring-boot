package com.mealrecommendationapp.service;

import com.mealrecommendationapp.dto.MenuItemDto;
import com.mealrecommendationapp.model.*;
import com.mealrecommendationapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantMenuItemRepository restaurantMenuItemRepository;
    @Autowired
    private RestaurantMenuItemRecommendRepository restaurantMenuItemRecommendRepository;
    @Autowired
    private RestaurantMenuItemRateRepository restaurantMenuItemRateRepository;
    @Autowired
    private MenuItemReviewRepository menuItemReviewRepository;


    public List<MenuItem> getAllMenuItems() {
        return (List<MenuItem>) menuItemRepository.findAll();
    }

    @Transactional(readOnly = false)
    public RestaurantMenuItem saveMenuItem(int restaurantId, MenuItemDto menuItemDto) {
        /*Get restaurant giving restaurantId*/
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        /*Get menuItem by MenuItemId*/
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemDto.getMenuItemId());

        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem.setMenuItem(menuItem.get());
        restaurantMenuItem.setRestaurant(restaurant.get());
        restaurantMenuItem.setSmallPrice(menuItemDto.getSmallPrice());
        restaurantMenuItem.setMediumPrice(menuItemDto.getMediumPrice());
        restaurantMenuItem.setLargePrice(menuItemDto.getLargePrice());
        /*restaurantMenuItem.setAverageReview(0.0);
        restaurantMenuItem.setAverageRate(0.0);
        restaurantMenuItem.setAverageRecommend(0.0);
        restaurantMenuItem.setAverageFinal(0.0);*/
        return restaurantMenuItemRepository.save(restaurantMenuItem);
    }

    public List<RestaurantMenuItem> getRestaurantMenuItems(int restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        List<RestaurantMenuItem> restaurantMenuItems = restaurantMenuItemRepository.findByRestaurant(restaurant.get());
        return restaurantMenuItems;
    }

    public Optional<RestaurantMenuItem> getRestaurantMenuItem(int restaurantMenuItemId) {
        return restaurantMenuItemRepository.findById(restaurantMenuItemId);
    }

    public RestaurantMenuItemRecommend getMenuItemRecommendByMenuItemRateIdAndUserId(int menuItemId, int userId) {
        return restaurantMenuItemRecommendRepository.findByRestaurantMenuItemIdAndUserId(menuItemId, userId);
    }

    public RestaurantMenuItemRecommend saveRestaurantMenuItemRecommend(RestaurantMenuItemRecommend restaurantMenuItemRecommend) {
        return restaurantMenuItemRecommendRepository.save(restaurantMenuItemRecommend);
    }

    public double getAverageRecommendByMenuItemId(int menuItemId) {
        return restaurantMenuItemRecommendRepository.getRestaurantMenuItemAverageRecommend(menuItemId);
    }

    public double getFinalRating(RestaurantMenuItem restaurantMenuItem) {
        double avgRate = restaurantMenuItem.getAverageRate() == null ? 0.0 : restaurantMenuItem.getAverageRate();
        double avgRecommend = restaurantMenuItem.getAverageRecommend() == null ? 0.0 : restaurantMenuItem.getAverageRecommend();
        double avgReview = restaurantMenuItem.getAverageReview() == null ? 0.0 : restaurantMenuItem.getAverageReview();
        double finalAverage =  ((avgRate + avgRecommend + avgReview) / 3);

        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(finalAverage));
    }

    public RestaurantMenuItem saveRestaurantMenuItem(RestaurantMenuItem restaurantMenuItem) {
        return restaurantMenuItemRepository.save(restaurantMenuItem);
    }

    public RestaurantMenuItemRate getMenuItemRateByMenuItemRateIdAndUserId(int menuItemId, int userId) {
        return restaurantMenuItemRateRepository.findByRestaurantMenuItemIdAndUserId(menuItemId, userId);
    }

    public RestaurantMenuItemRate saveRestaurantMenuItemRate(RestaurantMenuItemRate restaurantMenuItemRate) {
        return restaurantMenuItemRateRepository.save(restaurantMenuItemRate);
    }

    public RestaurantMenuItemReview getMenuItemReviewByMenuItemReviewIdAndUserId(int menuItemId, int userId) {
        return menuItemReviewRepository.findByRestaurantMenuItemIdAndUserId(menuItemId, userId);
    }

    public RestaurantMenuItemReview saveRestaurantMenuItemReview(RestaurantMenuItemReview restaurantMenuItemReview) {
        return menuItemReviewRepository.save(restaurantMenuItemReview);
    }

    public double getRestaurantMenuItemAverageReview(int menuItemId) {
        return menuItemReviewRepository.getRestaurantMenuItemAverageRate(menuItemId);
    }

    public double getRestaurantMenuItemAverageRate(int menuItemId) {
        return restaurantMenuItemRateRepository.getRestaurantMenuItemAverageRate(menuItemId);
    }


    public List<RestaurantMenuItemReview> getAllCommentAccordingToMenuItem(int menuItemId) {

        List<RestaurantMenuItemReview> allMenuItemComment = menuItemReviewRepository.findAllByRestaurantMenuItemIdOrderByDateDescDateAsc(menuItemId);
        return allMenuItemComment;
    }

    public List<RestaurantMenuItem> getAllRestaurantMenuItems() {
        return (List<RestaurantMenuItem>) restaurantMenuItemRepository.findAll();
    }
}
