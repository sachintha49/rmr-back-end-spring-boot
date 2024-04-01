package com.mealrecommendationapp.service;

import com.mealrecommendationapp.dto.MenuItemDto;
import com.mealrecommendationapp.model.MenuItem;
import com.mealrecommendationapp.model.Restaurant;
import com.mealrecommendationapp.model.RestaurantMenuItem;
import com.mealrecommendationapp.model.RestaurantMenuItemRecommend;
import com.mealrecommendationapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        double avgRate = restaurantMenuItem.getAverageRate();
        double avgRecommend = restaurantMenuItem.getAverageRecommend();
        double avgReview = restaurantMenuItem.getAverageReview();
        return (avgRate + avgRecommend + avgReview / 3);
    }

    public RestaurantMenuItem saveRestaurantMenuItem(RestaurantMenuItem restaurantMenuItem) {
        return restaurantMenuItemRepository.save(restaurantMenuItem);
    }
}
